//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.7 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.02.11 at 03:50:38 PM MSK 
//


package ru.tinkoff.lections.receiver.resource.sync.soap.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.tinkoff.lections.receiver.resource.sync.soap.gen package. 
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

    private final static QName _GetMessageResponse_QNAME = new QName("sender-namespace", "getMessageResponse");
    private final static QName _GetRequestMessage_QNAME = new QName("sender-namespace", "getRequestMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.tinkoff.lections.receiver.resource.sync.soap.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMessageResponse }
     * 
     */
    public GetMessageResponse createGetMessageResponse() {
        return new GetMessageResponse();
    }

    /**
     * Create an instance of {@link GetRequestMessage }
     * 
     */
    public GetRequestMessage createGetRequestMessage() {
        return new GetRequestMessage();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link LocalDateTime }
     * 
     */
    public LocalDateTime createLocalDateTime() {
        return new LocalDateTime();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMessageResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetMessageResponse }{@code >}
     */
    @XmlElementDecl(namespace = "sender-namespace", name = "getMessageResponse")
    public JAXBElement<GetMessageResponse> createGetMessageResponse(GetMessageResponse value) {
        return new JAXBElement<GetMessageResponse>(_GetMessageResponse_QNAME, GetMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequestMessage }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetRequestMessage }{@code >}
     */
    @XmlElementDecl(namespace = "sender-namespace", name = "getRequestMessage")
    public JAXBElement<GetRequestMessage> createGetRequestMessage(GetRequestMessage value) {
        return new JAXBElement<GetRequestMessage>(_GetRequestMessage_QNAME, GetRequestMessage.class, null, value);
    }

}
