package ee.adit.pojo;

import java.util.Date;

public class DocumentSharingRecipient {
    private String code;
    private String name;
    private Boolean hasBeenViewed;
    private Date openedTime;
    private Long workflowStatusId;
    private Date sharedTime;
    private String reasonForSharing;
    private Boolean sharedForSigning;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasBeenViewed() {
        return hasBeenViewed;
    }

    public void setHasBeenViewed(Boolean hasBeenViewed) {
        this.hasBeenViewed = hasBeenViewed;
    }

    public Date getOpenedTime() {
        return openedTime;
    }

    public void setOpenedTime(Date openedTime) {
        this.openedTime = openedTime;
    }

    public Long getWorkflowStatusId() {
        return workflowStatusId;
    }

    public void setWorkflowStatusId(Long workflowStatusId) {
        this.workflowStatusId = workflowStatusId;
    }

	public Date getSharedTime() {
		return sharedTime;
	}

	public void setSharedTime(Date sharedTime) {
		this.sharedTime = sharedTime;
	}

	public String getReasonForSharing() {
		return reasonForSharing;
	}

	public void setReasonForSharing(String reasonForSharing) {
		this.reasonForSharing = reasonForSharing;
	}

	public Boolean getSharedForSigning() {
		return sharedForSigning;
	}

	public void setSharedForSigning(Boolean sharedForSigning) {
		this.sharedForSigning = sharedForSigning;
	}
}
