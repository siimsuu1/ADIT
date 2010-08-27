package ee.adit.ws.endpoint.user;

import java.util.Calendar;
import java.util.Locale;

import org.apache.log4j.Logger;

import ee.adit.dao.pojo.AditUser;
import ee.adit.dao.pojo.Usertype;
import ee.adit.exception.AditCodedException;
import ee.adit.exception.AditInternalException;
import ee.adit.pojo.ArrayOfMessage;
import ee.adit.pojo.JoinRequest;
import ee.adit.pojo.JoinResponse;
import ee.adit.pojo.Message;
import ee.adit.pojo.Success;
import ee.adit.service.LogService;
import ee.adit.service.UserService;
import ee.adit.util.CustomXTeeHeader;
import ee.adit.util.Util;
import ee.adit.ws.endpoint.AbstractAditBaseEndpoint;

/**
 * 
 * Web-service endpoint class for "join" service
 * 
 * @author Marko Kurm, Microlink Eesti AS, marko.kurm@microlink.ee
 *
 */
public class JoinEndpoint extends AbstractAditBaseEndpoint {

	private static Logger LOG = Logger.getLogger(JoinEndpoint.class);

	private UserService userService;
	
	@Override
	protected Object invokeInternal(Object requestObject, int version) throws Exception {

		LOG.debug("JoinEndpoint invoked. Version: " + version);
		
		if(version == 1) {
			return v1(requestObject);
		} else {
			throw new AditInternalException("This method does not support version specified: " + version);
		}
		
	}

	protected Object v1(Object requestObject) throws Exception {
		JoinResponse response = new JoinResponse();
		ArrayOfMessage messages = new ArrayOfMessage();
		Calendar requestDate = Calendar.getInstance();
		String additionalInformationForLog = null;
		Long documentId = null;
		
		try {

			
			JoinRequest request = (JoinRequest) requestObject;
			CustomXTeeHeader header = this.getHeader();
			String applicationName = header.getInfosysteem();
			
			// Log request
			Util.printHeader(header);
			printRequest(request);

			// Check header for required fields
			checkHeader(header);
			
			// Check request body
			checkRequest(request);
			
			// Kontrollime, kas p�ringu k�ivitanud infos�steem on ADITis registreeritud
			boolean applicationRegistered = this.getUserService().isApplicationRegistered(applicationName);

			if (applicationRegistered) {
				
				// Kontrollime, kas päringu käivitanud infosüsteem tohib
				// andmeid muuta (või üldse näha)
				int accessLevel = this.getUserService().getAccessLevel(applicationName);
				
				// Application has write permission
				if(accessLevel == 2) {
					
					// Kontrollime, kas etteantud kasutajatüüp eksisteerib
					Usertype usertype = this.getUserService().getUsertypeByID(request.getUserType());
					
					if(usertype != null) {

						// Kontrollime, kas kasutaja juba eksisteerib
						// s.t. kas lisame uue kasutaja või muudame olemasolevat
						LOG.debug("Checking if user already exists...");
						String userCode = ((this.getHeader().getAllasutus() != null) && (this.getHeader().getAllasutus().length() > 0)) ? this.getHeader().getAllasutus() : this.getHeader().getIsikukood();
						AditUser aditUser = userService.getUserByID(userCode);
						
						// Lisame kasutaja või muudame olemasolevat
						if(aditUser != null) { 
							// Muudame olemasolevat kasutajat
							// Kontrollime, kas infosüsteemil on õigus kasutaja andmeid muuta
							int applicationAccessLevelForUser = userService.getAccessLevelForUser(applicationName, aditUser);
							
							if(applicationAccessLevelForUser == 2) {
								LOG.info("Modifying existing user.");
								boolean userReactivated = userService.modifyUser(aditUser, request.getUserName(), usertype);
								
								String message =  null;
								if(userReactivated) {
									message = this.getMessageService().getMessage("request.join.success.userReactivated", new Object[] { request.getUserType() }, Locale.ENGLISH);
									messages.setMessage(this.getMessageService().getMessages("request.join.success.userReactivated", new Object[] { request.getUserType() }));
								} else {
									message = this.getMessageService().getMessage("request.join.success.userModified", new Object[] { request.getUserType() }, Locale.ENGLISH);
									messages.setMessage(this.getMessageService().getMessages("request.join.success.userModified", new Object[] { request.getUserType() }));
								}
								
								response.setSuccess(new Success(true));
								additionalInformationForLog = "SUCCESS: " + message;
								
							} else {								
								AditCodedException aditCodedException = new AditCodedException("application.insufficientPrivileges.forUser.write");
								aditCodedException.setParameters(new Object[] { applicationName, aditUser.getUserCode() });
								throw aditCodedException;
							}
						} else {
							LOG.info("Adding new user.");
							userService.addUser(request.getUserName(), usertype, header.getAllasutus(), header.getIsikukood());
							response.setSuccess(new Success(true));
							String message = this.getMessageService().getMessage("request.join.success.userAdded", new Object[] { request.getUserType() }, Locale.ENGLISH);
							additionalInformationForLog = "SUCCESS: " + message;
							messages.setMessage(this.getMessageService().getMessages("request.join.success.userAdded", new Object[] { request.getUserType() }));
						}
					} else {
						String usertypes = this.getUserService().getUsertypesString();
						AditCodedException aditCodedException = new AditCodedException("usertype.nonExistent");
						aditCodedException.setParameters(new Object[] { request.getUserType(), usertypes });
						throw aditCodedException;						
					}
					
				} else {
					AditCodedException aditCodedException = new AditCodedException("application.insufficientPrivileges.write");
					aditCodedException.setParameters(new Object[] { applicationName });
					throw aditCodedException;
				}
				
			} else {
				AditCodedException aditCodedException = new AditCodedException("application.notRegistered");
				aditCodedException.setParameters(new Object[] { applicationName });
				throw aditCodedException;
			}
			
			// Set response messages
			response.setMessages(messages);
			
		} catch (Exception e) {
			LOG.error("Exception: ", e);
			additionalInformationForLog = "ERROR: " + e.getMessage();
			super.logError(documentId, requestDate.getTime(), LogService.ErrorLogLevel_Error, e.getMessage());
			
			response.setSuccess(new Success(false));
			ArrayOfMessage arrayOfMessage = new ArrayOfMessage();
			
			if(e instanceof AditCodedException) {
				LOG.debug("Adding exception messages to response object.");
				arrayOfMessage.setMessage(this.getMessageService().getMessages((AditCodedException) e));
			} else {
				arrayOfMessage.getMessage().add(new Message("en", "Service error"));
			}
			
			LOG.debug("Adding exception messages to response object.");
			response.setMessages(arrayOfMessage);
		}
		
		super.logCurrentRequest(documentId, requestDate.getTime(), additionalInformationForLog);
		return response;
	}
	
	@Override
	protected Object getResultForGenericException(Exception ex) {
		super.logError(null, Calendar.getInstance().getTime(), LogService.ErrorLogLevel_Fatal, ex.getMessage());
		JoinResponse response = new JoinResponse();
		response.setSuccess(new Success(false));
		ArrayOfMessage arrayOfMessage = new ArrayOfMessage();
		arrayOfMessage.getMessage().add(new Message("en", ex.getMessage()));
		response.setMessages(arrayOfMessage);
		return response;
	}
	
	private void checkRequest(JoinRequest request) {
		if(request != null) {
			if(request.getUserType() == null || "".equalsIgnoreCase(request.getUserType().trim())) {
				throw new AditCodedException("request.body.undefined.usertype");
			} else if(request.getUserName() == null || "".equalsIgnoreCase(request.getUserName().trim())) {
				throw new AditCodedException("request.body.undefined.username");
			}
		} else {
			throw new AditCodedException("request.body.empty");
		}
	}
	
	private static void printRequest(JoinRequest request) {

		LOG.debug("-------- JoinRequest -------");
		LOG.debug("UserName: " + request.getUserName());
		LOG.debug("UserType: " + request.getUserType());
		LOG.debug("----------------------------");

	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}