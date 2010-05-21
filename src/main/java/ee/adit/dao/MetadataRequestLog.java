package ee.adit.dao;

// Generated 21.05.2010 14:01:22 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * MetadataRequestLog generated by hbm2java
 */
public class MetadataRequestLog implements java.io.Serializable {

	private long id;
	private long documentId;
	private Date requestDate;
	private String remoteApplicationShortName;
	private String userCode;
	private String organizationCode;

	public MetadataRequestLog() {
	}

	public MetadataRequestLog(long id, long documentId) {
		this.id = id;
		this.documentId = documentId;
	}

	public MetadataRequestLog(long id, long documentId, Date requestDate,
			String remoteApplicationShortName, String userCode,
			String organizationCode) {
		this.id = id;
		this.documentId = documentId;
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

	public long getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(long documentId) {
		this.documentId = documentId;
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
