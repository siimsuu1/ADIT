//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.07.29 at 12:51:34 PM EEST 
//

package ee.adit.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfEmailAddress complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfEmailAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="email_address" type="{http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid}EmailAddress" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfEmailAddress", propOrder = {"emailAddress" })
public class ArrayOfEmailAddress {

    @XmlElement(name = "email_address")
    private List<EmailAddress> emailAddress;

    /**
     * Gets the value of the emailAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the emailAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getEmailAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmailAddress }
     * 
     * 
     */
    public List<EmailAddress> getEmailAddress() {
        if (emailAddress == null) {
            emailAddress = new ArrayList<EmailAddress>();
        }
        return this.emailAddress;
    }

    public void setEmailAddress(List<EmailAddress> emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void addEmailAddress(EmailAddress address) {
        if (this.emailAddress == null) {
            this.emailAddress = new ArrayList<EmailAddress>();
        }
        this.emailAddress.add(address);
    }
}
