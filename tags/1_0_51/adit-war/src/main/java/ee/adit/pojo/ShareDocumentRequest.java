//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.07.08 at 02:57:06 PM EEST 
//

package ee.adit.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ShareDocumentRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ShareDocumentRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="document_id" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="recipient_list" type="{http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid}ArrayOfUserCode"/>
 *         &lt;element name="reason_for_sharing" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shared_for_signing" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShareDocumentRequest", propOrder = {"documentId", "recipientList", "reasonForSharing",
        "sharedForSigning" })
public class ShareDocumentRequest {

    @XmlElement(name = "document_id", required = true)
    private Long documentId;
    @XmlElement(name = "recipient_list", required = true)
    private ArrayOfUserCode recipientList;
    @XmlElement(name = "reason_for_sharing")
    private String reasonForSharing;
    @XmlElement(name = "shared_for_signing")
    private Boolean sharedForSigning;

    /**
     * Gets the value of the documentId property.
     * 
     */
    public Long getDocumentId() {
        return documentId;
    }

    /**
     * Sets the value of the documentId property.
     * 
     */
    public void setDocumentId(Long value) {
        this.documentId = value;
    }

    /**
     * Gets the value of the recipientList property.
     * 
     * @return possible object is {@link ArrayOfUserCode }
     * 
     */
    public ArrayOfUserCode getRecipientList() {
        return recipientList;
    }

    /**
     * Sets the value of the recipientList property.
     * 
     * @param value
     *            allowed object is {@link ArrayOfUserCode }
     * 
     */
    public void setRecipientList(ArrayOfUserCode value) {
        this.recipientList = value;
    }

    /**
     * Gets the value of the reasonForSharing property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getReasonForSharing() {
        return reasonForSharing;
    }

    /**
     * Sets the value of the reasonForSharing property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setReasonForSharing(String value) {
        this.reasonForSharing = value;
    }

    /**
     * Gets the value of the sharedForSigning property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean getSharedForSigning() {
        return sharedForSigning;
    }

    /**
     * Sets the value of the sharedForSigning property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setSharedForSigning(Boolean value) {
        this.sharedForSigning = value;
    }

}
