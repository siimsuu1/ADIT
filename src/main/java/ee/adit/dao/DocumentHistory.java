package ee.adit.dao;

// Generated 21.05.2010 14:11:24 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * DocumentHistory generated by hbm2java
 */
public class DocumentHistory implements java.io.Serializable {

	private long id;
	private long documentId;
	private Long documentHistoryTypeId;
	private String description;
	private Date eventDate;
	private String userCode;
	private String remoteApplicationShortName;
	private String notificationStatus;
	private String xteeNotificationId;

	public DocumentHistory() {
	}

	public DocumentHistory(long id, long documentId) {
		this.id = id;
		this.documentId = documentId;
	}

	public DocumentHistory(long id, long documentId,
			Long documentHistoryTypeId, String description, Date eventDate,
			String userCode, String remoteApplicationShortName,
			String notificationStatus, String xteeNotificationId) {
		this.id = id;
		this.documentId = documentId;
		this.documentHistoryTypeId = documentHistoryTypeId;
		this.description = description;
		this.eventDate = eventDate;
		this.userCode = userCode;
		this.remoteApplicationShortName = remoteApplicationShortName;
		this.notificationStatus = notificationStatus;
		this.xteeNotificationId = xteeNotificationId;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(long documentId) {
		this.documentId = documentId;
	}

	public Long getDocumentHistoryTypeId() {
		return this.documentHistoryTypeId;
	}

	public void setDocumentHistoryTypeId(Long documentHistoryTypeId) {
		this.documentHistoryTypeId = documentHistoryTypeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getRemoteApplicationShortName() {
		return this.remoteApplicationShortName;
	}

	public void setRemoteApplicationShortName(String remoteApplicationShortName) {
		this.remoteApplicationShortName = remoteApplicationShortName;
	}

	public String getNotificationStatus() {
		return this.notificationStatus;
	}

	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public String getXteeNotificationId() {
		return this.xteeNotificationId;
	}

	public void setXteeNotificationId(String xteeNotificationId) {
		this.xteeNotificationId = xteeNotificationId;
	}

}
