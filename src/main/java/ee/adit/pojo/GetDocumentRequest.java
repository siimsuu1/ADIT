//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.07.07 at 10:40:05 AM EEST 
//


package ee.adit.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetDocumentRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetDocumentRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="document_id" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="include_file_contents" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDocumentRequest", propOrder = {
    "documentId",
    "includeFileContents"
})
public class GetDocumentRequest {

    @XmlElement(name = "document_id", required = true)
    protected long documentId;
    @XmlElement(name = "include_file_contents")
    protected Boolean includeFileContents;

    /**
     * Gets the value of the documentId property.
     * 
     */
    public long getDocumentId() {
        return documentId;
    }

    /**
     * Sets the value of the documentId property.
     * 
     */
    public void setDocumentId(long value) {
        this.documentId = value;
    }

    /**
     * Gets the value of the includeFileContents property.
     * 
     */
    public Boolean isIncludeFileContents() {
        return includeFileContents;
    }

    /**
     * Sets the value of the includeFileContents property.
     * 
     */
    public void setIncludeFileContents(Boolean value) {
        this.includeFileContents = value;
    }

}
