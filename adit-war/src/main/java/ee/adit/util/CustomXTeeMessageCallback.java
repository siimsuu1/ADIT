package ee.adit.util;

import java.util.Collection;
import java.util.Random;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import ee.webmedia.xtee.client.exception.XTeeException;
import ee.webmedia.xtee.client.service.XTeeAttachment;
import ee.webmedia.xtee.client.service.XTeeServiceConfiguration;

public class CustomXTeeMessageCallback implements WebServiceMessageCallback {

	private static Logger LOG = Logger.getLogger(CustomXTeeMessageCallback.class);
	
	protected Random random = new Random(System.currentTimeMillis());

	private CustomXTeeServiceConfiguration serviceConfigurator;
	private Collection<XTeeAttachment> attachments;

	public CustomXTeeMessageCallback(CustomXTeeServiceConfiguration serviceConfigurator, Collection<XTeeAttachment> attachments) {
		LOG.debug("Creating CustomXTeeMessageCallback.");
		this.serviceConfigurator = serviceConfigurator;
		this.attachments = attachments;
	}

	/**
   *  
   */
	public void doWithMessage(WebServiceMessage message) {
		LOG.debug("CustomXTeeMessageCallback doWithMessage...");
		SaajSoapMessage messag = (SaajSoapMessage) message;
		// Add attachments
		if (attachments != null) {
			for (XTeeAttachment attachment : attachments) {
				messag.addAttachment(attachment.getCid(), attachment, attachment.getContentType());
			}
		}
		try {
			SOAPMessage soapmess = messag.getSaajMessage();
			SOAPEnvelope env = soapmess.getSOAPPart().getEnvelope();
			addNamespaces(env);
			addXTeeHeaderElements(env);
		} catch (SOAPException e) {
			throw new XTeeException("Setting x-tee header elements or namespaces failed", e);
		}
	}

	private void addXTeeHeaderElements(SOAPEnvelope env) throws SOAPException {
		LOG.debug("CustomXTeeMessageCallback adding header elements");
		SOAPHeader header = env.getHeader();
		SOAPElement pasutus = header.addChildElement("asutus", "ns4");
		SOAPElement pandmekogu = header.addChildElement("andmekogu", "ns4");
		SOAPElement ikood = header.addChildElement("isikukood", "ns4");
		SOAPElement id = header.addChildElement("id", "ns4");
		SOAPElement pnimi = header.addChildElement("nimi", "ns4");
		SOAPElement toimikEl = header.addChildElement("toimik", "ns4");
		SOAPElement infosysteem = header.addChildElement("infosysteem", "ns5");
		pasutus.addAttribute(env.createName("xsi:type"), "xsd:string");
		pandmekogu.addAttribute(env.createName("xsi:type"), "xsd:string");
		ikood.addAttribute(env.createName("xsi:type"), "xsd:string");
		id.addAttribute(env.createName("xsi:type"), "xsd:string");
		pnimi.addAttribute(env.createName("xsi:type"), "xsd:string");
		pasutus.addTextNode(serviceConfigurator.getInstitution());
		pandmekogu.addTextNode(serviceConfigurator.getDatabase());
		ikood.addTextNode(serviceConfigurator.getIdCode());
		id.addTextNode(generateUniqueMessageId());
		StringBuilder sb = new StringBuilder(serviceConfigurator.getDatabase());
		sb.append(".");
		sb.append(serviceConfigurator.getMethod());
		sb.append(".");
		sb.append(serviceConfigurator.getVersion());
		pnimi.addTextNode(sb.toString());
		String toimik = serviceConfigurator.getToimik();
		toimikEl.addTextNode(toimik != null ? toimik : "");
		infosysteem.addAttribute(env.createName("xsi:type"), "xsd:string");
		infosysteem.addTextNode(serviceConfigurator.getInfosysteem());
	}

	/**
	 * Unique identifier for service invocation, consisting of numbers and
	 * letters of the Latin alphabet. The identifier is generated by service
	 * invoker, who must guarantee that the identifier is globally unique.
	 */
	private String generateUniqueMessageId() {
		return Long.toHexString(System.currentTimeMillis()) + serviceConfigurator.getInstitution() + random.nextInt();
	}

	/**
	 * sets envelope namespaces to SOAP envelope
	 */
	private void addNamespaces(SOAPEnvelope env) throws SOAPException {
		LOG.debug("CustomXTeeMessageCallback adding namespaces");
		env.addNamespaceDeclaration("xs", "http://www.w3.org/2001/XMLSchema");
		env.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		env.addNamespaceDeclaration("SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/");
		env.addNamespaceDeclaration("ns4", "http://x-tee.riik.ee/xsd/xtee.xsd");
		env.addNamespaceDeclaration("ns5", "http://producers." + serviceConfigurator.getDatabase() + ".xtee.riik.ee/producer/" + serviceConfigurator.getDatabase());
		env.setEncodingStyle("http://schemas.xmlsoap.org/soap/encoding/");
	}
}