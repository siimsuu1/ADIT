package ee.adit.dao.pojo;

// Generated 21.06.2010 14:02:03 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Document generated by hbm2java
 */
public class Document implements java.io.Serializable {

    private static final long serialVersionUID = -4634603111062567273L;
    private long id;
    private String documentType;
    private Document document;
    private String guid;
    private String title;
    private String creatorCode;
    private String creatorName;
    private String creatorUserCode;
    private String creatorUserName;
    private Date creationDate;
    private String remoteApplication;
    private Date lastModifiedDate;
    private Long documentDvkStatusId;
    private Long dvkId;
    private Long documentWfStatusId;
    private Boolean locked;
    private Date lockingDate;
    private Boolean signable;
    private Boolean deflated;
    private Date deflateDate;
    private Boolean deleted;
    private Boolean invisibleToOwner;
    private Boolean signed;
    private Long filesSizeBytes;
    private String senderReceiver;
    private Long eformUseId;
    private Set<Document> documents = new HashSet<Document>(0);
    private Set<DocumentFile> documentFiles = new HashSet<DocumentFile>(0);
    private Set<Signature> signatures = new HashSet<Signature>(0);
    private Set<DocumentHistory> documentHistories = new HashSet<DocumentHistory>(0);
    private Set<DocumentSharing> documentSharings = new HashSet<DocumentSharing>(0);

    public Document() {
    }

    public Document(long id, String documentType, String creatorCode, String creatorUserCode) {
        this.id = id;
        this.documentType = documentType;
        this.creatorCode = creatorCode;
        this.creatorUserCode = creatorUserCode;
    }

    public Document(long id, String documentType, Document document, String guid, String title, String creatorCode,
            String creatorName, String creatorUserCode, String creatorUserName, Date creationDate,
            String remoteApplication, Date lastModifiedDate, Long documentDvkStatusId, Long dvkId,
            Long documentWfStatusId, Boolean locked, Date lockingDate, Boolean signable, Boolean deflated,
            Date deflateDate, Boolean deleted, Boolean invisibleToOwner, Boolean signed,
            Set<Document> documents, Set<DocumentFile> documentFiles, Set<Signature> signatures,
            Set<DocumentHistory> documentHistories, Set<DocumentSharing> documentSharings) {
        this.id = id;
        this.documentType = documentType;
        this.document = document;
        this.guid = guid;
        this.title = title;
        this.creatorCode = creatorCode;
        this.creatorName = creatorName;
        this.creatorUserCode = creatorUserCode;
        this.creatorUserName = creatorUserName;
        this.creationDate = creationDate;
        this.remoteApplication = remoteApplication;
        this.lastModifiedDate = lastModifiedDate;
        this.documentDvkStatusId = documentDvkStatusId;
        this.dvkId = dvkId;
        this.documentWfStatusId = documentWfStatusId;
        this.locked = locked;
        this.lockingDate = lockingDate;
        this.signable = signable;
        this.deflated = deflated;
        this.deflateDate = deflateDate;
        this.deleted = deleted;
        this.invisibleToOwner = invisibleToOwner;
        this.signed = signed;
        this.documents = documents;
        this.documentFiles = documentFiles;
        this.signatures = signatures;
        this.documentHistories = documentHistories;
        this.documentSharings = documentSharings;
    }

	public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatorCode() {
        return this.creatorCode;
    }

    public void setCreatorCode(String creatorCode) {
        this.creatorCode = creatorCode;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorUserCode() {
        return creatorUserCode;
    }

    public void setCreatorUserCode(String creatorUserCode) {
        this.creatorUserCode = creatorUserCode;
    }

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getRemoteApplication() {
        return this.remoteApplication;
    }

    public void setRemoteApplication(String remoteApplication) {
        this.remoteApplication = remoteApplication;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getDocumentDvkStatusId() {
        return this.documentDvkStatusId;
    }

    public void setDocumentDvkStatusId(Long documentDvkStatusId) {
        this.documentDvkStatusId = documentDvkStatusId;
    }

    public Long getDvkId() {
        return this.dvkId;
    }

    public void setDvkId(Long dvkId) {
        this.dvkId = dvkId;
    }

    public Long getDocumentWfStatusId() {
        return this.documentWfStatusId;
    }

    public void setDocumentWfStatusId(Long documentWfStatusId) {
        this.documentWfStatusId = documentWfStatusId;
    }

    public Boolean getLocked() {
        return this.locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Date getLockingDate() {
        return this.lockingDate;
    }

    public void setLockingDate(Date lockingDate) {
        this.lockingDate = lockingDate;
    }

    public Boolean getSignable() {
        return this.signable;
    }

    public void setSignable(Boolean signable) {
        this.signable = signable;
    }

    public Boolean getDeflated() {
        return this.deflated;
    }

    public void setDeflated(Boolean deflated) {
        this.deflated = deflated;
    }

    public Date getDeflateDate() {
        return this.deflateDate;
    }

    public void setDeflateDate(Date deflateDate) {
        this.deflateDate = deflateDate;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getInvisibleToOwner() {
		return invisibleToOwner;
	}

	public void setInvisibleToOwner(Boolean invisibleToOwner) {
		this.invisibleToOwner = invisibleToOwner;
	}

	public Boolean getSigned() {
        return this.signed;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }

    public Long getEformUseId() {
		return eformUseId;
	}

	public void setEformUseId(Long eformUseId) {
		this.eformUseId = eformUseId;
	}

	public Set<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<DocumentFile> getDocumentFiles() {
        return this.documentFiles;
    }

    public void setDocumentFiles(Set<DocumentFile> documentFiles) {
        this.documentFiles = documentFiles;
    }

    public Set<Signature> getSignatures() {
        return this.signatures;
    }

    public void setSignatures(Set<Signature> signatures) {
        this.signatures = signatures;
    }

    public Set<DocumentHistory> getDocumentHistories() {
        return this.documentHistories;
    }

    public void setDocumentHistories(Set<DocumentHistory> documentHistories) {
        this.documentHistories = documentHistories;
    }

    public Set<DocumentSharing> getDocumentSharings() {
        return this.documentSharings;
    }

    public void setDocumentSharings(Set<DocumentSharing> documentSharings) {
        this.documentSharings = documentSharings;
    }

	public Long getFilesSizeBytes() {
		return filesSizeBytes;
	}

	public void setFilesSizeBytes(Long filesSizeBytes) {
		this.filesSizeBytes = filesSizeBytes;
	}

	public String getSenderReceiver() {
		return senderReceiver;
	}

	public void setSenderReceiver(String senderReceiver) {
		this.senderReceiver = senderReceiver;
	}
}
