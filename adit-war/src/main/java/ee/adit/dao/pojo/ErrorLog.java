package ee.adit.dao.pojo;

// Generated 21.06.2010 14:02:03 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * ErrorLog generated by hbm2java
 */
public class ErrorLog implements java.io.Serializable {

	private long id;
	private Long documentId;
	private Date errorDate;
	private String remoteApplicationShortName;
	private String userCode;
	private String actionName;
	private String errorLevel;
	private String errorMessage;

	public ErrorLog() {
	}

	public ErrorLog(long id) {
		this.id = id;
	}

	public ErrorLog(long id, Long documentId, Date errorDate,
			String remoteApplicationShortName, String userCode,
			String actionName, String errorLevel, String errorMessage) {
		this.id = id;
		this.documentId = documentId;
		this.errorDate = errorDate;
		this.remoteApplicationShortName = remoteApplicationShortName;
		this.userCode = userCode;
		this.actionName = actionName;
		this.errorLevel = errorLevel;
		this.errorMessage = errorMessage;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Date getErrorDate() {
		return this.errorDate;
	}

	public void setErrorDate(Date errorDate) {
		this.errorDate = errorDate;
	}

	public String getRemoteApplicationShortName() {
		return this.remoteApplicationShortName;
	}

	public void setRemoteApplicationShortName(String remoteApplicationShortName) {
		this.remoteApplicationShortName = remoteApplicationShortName;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getErrorLevel() {
		return this.errorLevel;
	}

	public void setErrorLevel(String errorLevel) {
		this.errorLevel = errorLevel;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}