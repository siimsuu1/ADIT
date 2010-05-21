package ee.adit.dao;

// Generated 21.05.2010 14:11:24 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * DownloadRequestLog generated by hbm2java
 */
public class DownloadRequestLog implements java.io.Serializable {

	private long id;
	private Long documentId;
	private Long documentFileId;
	private Date requestDate;
	private String remoteApplicationShortName;
	private String userCode;
	private String organizationCode;

	public DownloadRequestLog() {
	}

	public DownloadRequestLog(long id) {
		this.id = id;
	}

	public DownloadRequestLog(long id, Long documentId, Long documentFileId,
			Date requestDate, String remoteApplicationShortName,
			String userCode, String organizationCode) {
		this.id = id;
		this.documentId = documentId;
		this.documentFileId = documentFileId;
		this.requestDate = requestDate;
		this.remoteApplicationShortName = remoteApplicationShortName;
		this.userCode = userCode;
		this.organizationCode = organizationCode;
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

	public Long getDocumentFileId() {
		return this.documentFileId;
	}

	public void setDocumentFileId(Long documentFileId) {
		this.documentFileId = documentFileId;
	}

	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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

	public String getOrganizationCode() {
		return this.organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

}
