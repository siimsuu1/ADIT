package ee.adit.ws.endpoint.document;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import ee.adit.dao.pojo.AditUser;
import ee.adit.dao.pojo.Document;
import ee.adit.dao.pojo.DocumentHistory;
import ee.adit.exception.AditCodedException;
import ee.adit.exception.AditInternalException;
import ee.adit.pojo.ArrayOfMessage;
import ee.adit.pojo.DeleteDocumentFileRequest;
import ee.adit.pojo.DeleteDocumentFileResponse;
import ee.adit.pojo.Message;
import ee.adit.pojo.Success;
import ee.adit.service.DocumentService;
import ee.adit.service.LogService;
import ee.adit.service.UserService;
import ee.adit.util.CustomXTeeHeader;
import ee.adit.util.Util;
import ee.adit.ws.endpoint.AbstractAditBaseEndpoint;
import ee.webmedia.xtee.annotation.XTeeService;

@XTeeService(name = "deleteDocumentFile", version = "v1")
@Component
public class DeleteDocumentFileEndpoint extends AbstractAditBaseEndpoint {

	private static Logger LOG = Logger.getLogger(DeleteDocumentFileEndpoint.class);
	private UserService userService;
	private DocumentService documentService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
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
		DeleteDocumentFileResponse response = new DeleteDocumentFileResponse();
		ArrayOfMessage messages = new ArrayOfMessage();
		Calendar requestDate = Calendar.getInstance();
		String additionalInformationForLog = null;
		Long documentId = null;
		
		try {
			LOG.debug("deleteDocumentFile.v1 invoked.");
			DeleteDocumentFileRequest request = (DeleteDocumentFileRequest) requestObject;
			if (request != null) {
				documentId = request.getDocumentId();
			}
			CustomXTeeHeader header = this.getHeader();
			String applicationName = header.getInfosysteem();
			
			// Log request
			Util.printHeader(header);
			printRequest(request);

			// Check header for required fields
			checkHeader(header);
			
			// Check request body
			checkRequest(request);
			
			// Kontrollime, kas päringu käivitanud infosüsteem on ADITis registreeritud
			this.getUserService().checkApplicationRegistered(applicationName);

			// Kontrollime, kas päringu käivitanud infosüsteem tohib
			// andmeid muuta (või üldse näha)
			this.getUserService().checkApplicationWritePrivilege(applicationName);
			
			// Kontrollime, kas päringus märgitud isik on teenuse kasutaja
			String userCode = ((this.getHeader().getAllasutus() != null) && (this.getHeader().getAllasutus().length() > 0)) ? this.getHeader().getAllasutus() : this.getHeader().getIsikukood(); 
			AditUser user = this.getUserService().getUserByID(userCode);
			if(user == null) {
				AditCodedException aditCodedException = new AditCodedException("user.nonExistent");
				aditCodedException.setParameters(new Object[] { userCode });
				throw aditCodedException;
			}
			AditUser xroadRequestUser = null;
			if (user.getUsertype().getShortName().equalsIgnoreCase("person")) {
				xroadRequestUser = user;
			} else {
				try {
					xroadRequestUser = this.getUserService().getUserByID(header.getIsikukood());
				} catch (Exception ex) {
					LOG.debug("Error when attempting to find local user matchinig the person that executed a company request.");
				}
			}
			
			// Kontrollime, et kasutajakonto ligipääs poleks peatatud (kasutaja lahkunud)
			if((user.getActive() == null) || !user.getActive()) {
				AditCodedException aditCodedException = new AditCodedException("user.inactive");
				aditCodedException.setParameters(new Object[] { userCode });
				throw aditCodedException;
			}
			
			// Check whether or not the application has rights to
			// modify current user's data.
			int applicationAccessLevelForUser = userService.getAccessLevelForUser(applicationName, user);
			if(applicationAccessLevelForUser != 2) {
				AditCodedException aditCodedException = new AditCodedException("application.insufficientPrivileges.forUser.write");
				aditCodedException.setParameters(new Object[] { applicationName, user.getUserCode() });
				throw aditCodedException;
			}
			
			Document doc = this.documentService.getDocumentDAO().getDocument(request.getDocumentId());
			
			// Kontrollime, kas ID-le vastav dokument on olemas
			if (doc != null) {
				// Kontrollime, kas dokument kuulub päringu käivitanud kasutajale
				if (doc.getCreatorCode().equalsIgnoreCase(userCode)) {
					// Make sure that the document is not deleted
					// NB! doc.getDeleted() can be NULL
					if ((doc.getDeleted() != null) && doc.getDeleted()) {
						AditCodedException aditCodedException = new AditCodedException("document.deleted");
						aditCodedException.setParameters(new Object[] { documentId });
						throw aditCodedException;
					}
					
					// Make sure that the document is not locked
					// NB! doc.getLocked() can be NULL
					if ((doc.getLocked() != null) && doc.getLocked()) {
						AditCodedException aditCodedException = new AditCodedException("request.deleteDocumentFile.document.locked");
						aditCodedException.setParameters(new Object[] { documentId });
						throw aditCodedException;
					}
					
					String resultCode = this.documentService.deflateDocumentFile(request.getDocumentId(), request.getFileId(), true);
					if (resultCode.equalsIgnoreCase("already_deleted")) {
						AditCodedException aditCodedException = new AditCodedException("file.isDeleted");
						aditCodedException.setParameters(new Object[] { request.getFileId() });
						throw aditCodedException;
					} else if (resultCode.equalsIgnoreCase("file_does_not_exist")) {						
						AditCodedException aditCodedException = new AditCodedException("file.nonExistent");
						aditCodedException.setParameters(new Object[] { request.getFileId() });
						throw aditCodedException;
					} else if (resultCode.equalsIgnoreCase("file_does_not_belong_to_document")) {
						AditCodedException aditCodedException = new AditCodedException("file.doesNotBelongToDocument");
						aditCodedException.setParameters(new Object[] { request.getFileId(), request.getDocumentId() });
						throw aditCodedException;
					}
				} else {
					AditCodedException aditCodedException = new AditCodedException("document.doesNotBelongToUser");
					aditCodedException.setParameters(new Object[] { request.getDocumentId(), userCode });
					throw aditCodedException;
				}
			} else {				
				AditCodedException aditCodedException = new AditCodedException("document.nonExistent");
				aditCodedException.setParameters(new Object[] { request.getDocumentId() });
				throw aditCodedException;
			}
			
			// If deletion was successful then add history event
			DocumentHistory historyEvent = new DocumentHistory(
				DocumentService.HistoryType_DeleteFile,
				documentId,
				requestDate.getTime(),
				user,
				xroadRequestUser,
				header);
			this.getDocumentService().getDocumentHistoryDAO().save(historyEvent);
			
			// Set response messages
			response.setSuccess(new Success(true));
			messages.setMessage(this.getMessageService().getMessages("request.deleteDocumentFile.success", new Object[] { }));
			response.setMessages(messages);
		} catch (Exception e) {
			LOG.error("Exception: ", e);
			additionalInformationForLog = "Request failed: " + e.getMessage();
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
		DeleteDocumentFileResponse response = new DeleteDocumentFileResponse();
		response.setSuccess(new Success(false));
		ArrayOfMessage arrayOfMessage = new ArrayOfMessage();
		arrayOfMessage.getMessage().add(new Message("en", ex.getMessage()));
		response.setMessages(arrayOfMessage);
		return response;
	}
	
	private void checkRequest(DeleteDocumentFileRequest request) {
		if(request != null) {
			if (request.getDocumentId() <= 0) {
				throw new AditCodedException("request.body.undefined.documentId");
			} else if (request.getDocumentId() <= 0) {
				throw new AditCodedException("request.body.undefined.fileId");
			}
		} else {
			throw new AditCodedException("request.body.empty");
		}
	}
	
	private static void printRequest(DeleteDocumentFileRequest request) {
		LOG.debug("-------- DeleteDocumentFileRequest -------");
		LOG.debug("Document ID: " + String.valueOf(request.getDocumentId()));
		LOG.debug("File ID: " + String.valueOf(request.getFileId()));
		LOG.debug("------------------------------------------");
	}
}
