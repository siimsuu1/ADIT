package ee.adit.ws.endpoint.user;

import java.util.Calendar;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import ee.adit.dao.pojo.AditUser;
import ee.adit.exception.AditException;
import ee.adit.exception.AditInternalException;
import ee.adit.pojo.ArrayOfEmailAddress;
import ee.adit.pojo.ArrayOfMessage;
import ee.adit.pojo.ArrayOfNotification;
import ee.adit.pojo.EmailAddress;
import ee.adit.pojo.GetNotificationsResponse;
import ee.adit.pojo.Message;
import ee.adit.service.LogService;
import ee.adit.service.UserService;
import ee.adit.stateportal.NotificationStatus;
import ee.adit.stateportal.StatePortalClient;
import ee.adit.util.CustomXTeeHeader;
import ee.adit.util.Util;
import ee.adit.ws.endpoint.AbstractAditBaseEndpoint;
import ee.webmedia.xtee.annotation.XTeeService;

@XTeeService(name = "getNotifications", version = "v1")
@Component
public class GetNotificationsEndpoint extends AbstractAditBaseEndpoint {
	private static Logger LOG = Logger.getLogger(GetNotificationsEndpoint.class);
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected Object invokeInternal(Object requestObject, int version) throws Exception {
		LOG.debug("JoinEndpoint invoked. Version: " + version);

		if (version == 1) {
			return v1(requestObject);
		} else {
			throw new AditInternalException("This method does not support version specified: " + version);
		}
	}

	protected Object v1(Object requestObject) {
		GetNotificationsResponse response = new GetNotificationsResponse();
		ArrayOfMessage messages = new ArrayOfMessage();
		Calendar requestDate = Calendar.getInstance();
		String additionalInformationForLog = null;

		try {
			CustomXTeeHeader header = this.getHeader();
			String applicationName = header.getInfosysteem();

			// Log request
			Util.printHeader(header);

			// Check header for required fields
			checkHeader(header);

			// Kontrollime, kas päringu käivitanud infosüsteem on ADITis
			// registreeritud
			boolean applicationRegistered = this.getUserService().isApplicationRegistered(applicationName);
			if (!applicationRegistered) {
				String errorMessage = this.getMessageSource().getMessage("application.notRegistered", new Object[] { applicationName }, Locale.ENGLISH);
				throw new AditException(errorMessage);
			}

			// Kontrollime, kas päringu käivitanud infosüsteem tohib
			// andmeid näha
			int accessLevel = this.getUserService().getAccessLevel(applicationName);
			if (accessLevel < 1) {
				String errorMessage = this.getMessageSource().getMessage("application.insufficientPrivileges.write", new Object[] { applicationName }, Locale.ENGLISH);
				throw new AditException(errorMessage);
			}

			// Kontrollime, kas päringus märgitud isik on teenuse kasutaja
			String userCode = ((this.getHeader().getAllasutus() != null) && (this.getHeader().getAllasutus().length() > 0)) ? this.getHeader().getAllasutus() : this.getHeader().getIsikukood();
			AditUser user = this.getUserService().getUserByID(userCode);
			if (user == null) {
				String errorMessage = this.getMessageSource().getMessage("user.nonExistent", new Object[] { userCode }, Locale.ENGLISH);
				throw new AditException(errorMessage);
			}

			// Kontrollime, et kasutajakonto ligipääs poleks peatatud (kasutaja
			// lahkunud)
			if ((user.getActive() == null) || !user.getActive()) {
				String errorMessage = this.getMessageSource().getMessage("user.inactive", new Object[] { userCode }, Locale.ENGLISH);
				throw new AditException(errorMessage);
			}

			// Check whether or not the application has rights to
			// read current user's data.
			int applicationAccessLevelForUser = userService.getAccessLevelForUser(applicationName, user);
			if (applicationAccessLevelForUser < 1) {
				String errorMessage = this.getMessageSource().getMessage("application.insufficientPrivileges.forUser.read", new Object[] { applicationName, user.getUserCode() }, Locale.ENGLISH);
				throw new AditException(errorMessage);
			}

			// Set notification data
			ArrayOfNotification notificationList = this.getUserService().getNotifications(user.getUserCode());
			response.setNotifications(notificationList);

			// Get notification overall status and e-mail list
			// from 'riigiportaal' database
			NotificationStatus notificationStatus = StatePortalClient.getNotificationStatus(user.getUserCode(), this.getConfiguration().getSchedulerEventTypeName());
			if (notificationStatus != null) {
				response.setNotificationsActive(notificationStatus.getNotificationEmailStatus());
				if (notificationStatus.getEmailList() != null) {
					ArrayOfEmailAddress addressList = new ArrayOfEmailAddress();
					for (EmailAddress address : notificationStatus.getEmailList()) {
						addressList.addEmailAddress(address);
					}
					response.setAddressList(addressList);
				}
			}

			messages.addMessage(new Message("en", this.getMessageSource().getMessage("request.getNotifications.success", new Object[] {}, Locale.ENGLISH)));

			// Set response messages
			response.setMessages(messages);
			response.setSuccess(true);
		} catch (Exception e) {
			LOG.error("Exception: ", e);
			additionalInformationForLog = "Request failed: " + e.getMessage();
			super.logError(null, requestDate.getTime(), LogService.ErrorLogLevel_Error, e.getMessage());

			response.setSuccess(false);
			ArrayOfMessage arrayOfMessage = new ArrayOfMessage();

			if (e instanceof AditException) {
				LOG.debug("Adding exception message to response object.");
				arrayOfMessage.getMessage().add(new Message("en", e.getMessage()));
			} else {
				arrayOfMessage.getMessage().add(new Message("en", "Service error"));
			}

			LOG.debug("Adding exception messages to response object.");
			response.setMessages(arrayOfMessage);
		}

		super.logCurrentRequest(null, requestDate.getTime(), additionalInformationForLog);
		return response;
	}

	@Override
	protected Object getResultForGenericException(Exception ex) {
		super.logError(null, Calendar.getInstance().getTime(), LogService.ErrorLogLevel_Fatal, ex.getMessage());
		GetNotificationsResponse response = new GetNotificationsResponse();
		response.setSuccess(false);
		ArrayOfMessage arrayOfMessage = new ArrayOfMessage();
		arrayOfMessage.getMessage().add(new Message("en", ex.getMessage()));
		response.setMessages(arrayOfMessage);
		return response;
	}

	private void checkHeader(CustomXTeeHeader header) throws Exception {
		String errorMessage = null;
		if (header != null) {
			if (header.getIsikukood() == null) {
				errorMessage = this.getMessageSource().getMessage("request.header.undefined.personalCode", new Object[] {}, Locale.ENGLISH);
				throw new AditException(errorMessage);
			} else if (header.getInfosysteem() == null) {
				errorMessage = this.getMessageSource().getMessage("request.header.undefined.systemName", new Object[] {}, Locale.ENGLISH);
				throw new AditException(errorMessage);
			} else if (header.getAsutus() == null) {
				errorMessage = this.getMessageSource().getMessage("request.header.undefined.institution", new Object[] {}, Locale.ENGLISH);
				throw new AditException(errorMessage);
			}
		}
	}
}
