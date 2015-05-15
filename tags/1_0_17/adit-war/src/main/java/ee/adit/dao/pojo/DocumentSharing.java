package ee.adit.dao.pojo;

// Generated 21.06.2010 14:02:03 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * DocumentSharing generated by hbm2java
 */
public class DocumentSharing implements java.io.Serializable {

    private static final long serialVersionUID = 3218754276318168022L;
    private Long id;
    private String documentSharingType;
    private Long documentDvkStatus;
    private Long documentWfStatus;
    private long documentId;
    private String userCode;
    private String userName;
    private String taskDescription;
    private Date creationDate;
    private Date lastAccessDate;

    public DocumentSharing() {
    }

    public DocumentSharing(Long id, String documentSharingType, long documentId, String userCode) {
        this.id = id;
        this.documentSharingType = documentSharingType;
        this.documentId = documentId;
        this.userCode = userCode;
    }

    public DocumentSharing(Long id, String documentSharingType, Long documentDvkStatus, Long documentWfStatus,
            long documentId, String userCode, String userName, String taskDescription, Date creationDate,
            Date lastAccessDate) {
        this.id = id;
        this.documentSharingType = documentSharingType;
        this.documentDvkStatus = documentDvkStatus;
        this.documentWfStatus = documentWfStatus;
        this.documentId = documentId;
        this.userCode = userCode;
        this.userName = userName;
        this.taskDescription = taskDescription;
        this.creationDate = creationDate;
        this.lastAccessDate = lastAccessDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentSharingType() {
        return this.documentSharingType;
    }

    public void setDocumentSharingType(String documentSharingType) {
        this.documentSharingType = documentSharingType;
    }

    public Long getDocumentDvkStatus() {
        return this.documentDvkStatus;
    }

    public void setDocumentDvkStatus(Long documentDvkStatus) {
        this.documentDvkStatus = documentDvkStatus;
    }

    public Long getDocumentWfStatus() {
        return this.documentWfStatus;
    }

    public void setDocumentWfStatus(Long documentWfStatus) {
        this.documentWfStatus = documentWfStatus;
    }

    public long getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastAccessDate() {
        return this.lastAccessDate;
    }

    public void setLastAccessDate(Date lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

}
