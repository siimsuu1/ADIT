//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.14 at 10:41:16 AM EEST 
//


package ee.adit.pojo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ee.adit.pojo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetJoinedRequest_QNAME = new QName("http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", "GetJoinedRequest");
    private final static QName _GetDocumentRequest_QNAME = new QName("http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", "GetDocumentRequest");
    private final static QName _GetJoinedResponse_QNAME = new QName("http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", "GetJoinedResponse");
    private final static QName _JoinResponse_QNAME = new QName("http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", "JoinResponse");
    private final static QName _GetDocumentResponse_QNAME = new QName("http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", "GetDocumentResponse");
    private final static QName _UnJoinRequest_QNAME = new QName("http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", "UnJoinRequest");
    private final static QName _GetUserInfoRequest_QNAME = new QName("http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", "GetUserInfoRequest");
    private final static QName _JoinRequest_QNAME = new QName("http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", "JoinRequest");
    private final static QName _UnJoinResponse_QNAME = new QName("http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", "UnJoinResponse");
    private final static QName _GetUserInfoResponse_QNAME = new QName("http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", "GetUserInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ee.adit.pojo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDocumentRequest }
     * 
     */
    public GetDocumentRequest createGetDocumentRequest() {
        return new GetDocumentRequest();
    }

    /**
     * Create an instance of {@link GetUserInfoResponse }
     * 
     */
    public GetUserInfoResponse createGetUserInfoResponse() {
        return new GetUserInfoResponse();
    }

    /**
     * Create an instance of {@link GetDocumentResponse }
     * 
     */
    public GetDocumentResponse createGetDocumentResponse() {
        return new GetDocumentResponse();
    }

    /**
     * Create an instance of {@link GetJoinedResponse }
     * 
     */
    public GetJoinedResponse createGetJoinedResponse() {
        return new GetJoinedResponse();
    }

    /**
     * Create an instance of {@link GetJoinedRequest }
     * 
     */
    public GetJoinedRequest createGetJoinedRequest() {
        return new GetJoinedRequest();
    }

    /**
     * Create an instance of {@link ArrayOfMessage }
     * 
     */
    public ArrayOfMessage createArrayOfMessage() {
        return new ArrayOfMessage();
    }

    /**
     * Create an instance of {@link UnJoinResponse }
     * 
     */
    public UnJoinResponse createUnJoinResponse() {
        return new UnJoinResponse();
    }

    /**
     * Create an instance of {@link UnJoinRequest }
     * 
     */
    public UnJoinRequest createUnJoinRequest() {
        return new UnJoinRequest();
    }

    /**
     * Create an instance of {@link JoinResponse }
     * 
     */
    public JoinResponse createJoinResponse() {
        return new JoinResponse();
    }

    /**
     * Create an instance of {@link JoinRequest }
     * 
     */
    public JoinRequest createJoinRequest() {
        return new JoinRequest();
    }

    /**
     * Create an instance of {@link GetUserInfoRequest }
     * 
     */
    public GetUserInfoRequest createGetUserInfoRequest() {
        return new GetUserInfoRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetJoinedRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", name = "GetJoinedRequest")
    public JAXBElement<GetJoinedRequest> createGetJoinedRequest(GetJoinedRequest value) {
        return new JAXBElement<GetJoinedRequest>(_GetJoinedRequest_QNAME, GetJoinedRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDocumentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", name = "GetDocumentRequest")
    public JAXBElement<GetDocumentRequest> createGetDocumentRequest(GetDocumentRequest value) {
        return new JAXBElement<GetDocumentRequest>(_GetDocumentRequest_QNAME, GetDocumentRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetJoinedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", name = "GetJoinedResponse")
    public JAXBElement<GetJoinedResponse> createGetJoinedResponse(GetJoinedResponse value) {
        return new JAXBElement<GetJoinedResponse>(_GetJoinedResponse_QNAME, GetJoinedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", name = "JoinResponse")
    public JAXBElement<JoinResponse> createJoinResponse(JoinResponse value) {
        return new JAXBElement<JoinResponse>(_JoinResponse_QNAME, JoinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", name = "GetDocumentResponse")
    public JAXBElement<GetDocumentResponse> createGetDocumentResponse(GetDocumentResponse value) {
        return new JAXBElement<GetDocumentResponse>(_GetDocumentResponse_QNAME, GetDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnJoinRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", name = "UnJoinRequest")
    public JAXBElement<UnJoinRequest> createUnJoinRequest(UnJoinRequest value) {
        return new JAXBElement<UnJoinRequest>(_UnJoinRequest_QNAME, UnJoinRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserInfoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", name = "GetUserInfoRequest")
    public JAXBElement<GetUserInfoRequest> createGetUserInfoRequest(GetUserInfoRequest value) {
        return new JAXBElement<GetUserInfoRequest>(_GetUserInfoRequest_QNAME, GetUserInfoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", name = "JoinRequest")
    public JAXBElement<JoinRequest> createJoinRequest(JoinRequest value) {
        return new JAXBElement<JoinRequest>(_JoinRequest_QNAME, JoinRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnJoinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", name = "UnJoinResponse")
    public JAXBElement<UnJoinResponse> createUnJoinResponse(UnJoinResponse value) {
        return new JAXBElement<UnJoinResponse>(_UnJoinResponse_QNAME, UnJoinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid", name = "GetUserInfoResponse")
    public JAXBElement<GetUserInfoResponse> createGetUserInfoResponse(GetUserInfoResponse value) {
        return new JAXBElement<GetUserInfoResponse>(_GetUserInfoResponse_QNAME, GetUserInfoResponse.class, null, value);
    }

}
