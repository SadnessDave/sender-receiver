package ru.tinkoff.lections.sender.resource.sync.soap;

import ru.tinkoff.lections.sender.domain.Message;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "sender-namespace", name = "sender")
public interface SyncSoapSenderService {

    @WebResult(name = "message", targetNamespace = "sender-namespace")
    @RequestWrapper(
            localName = "getRequestMessage",
            targetNamespace = "sender-namespace",
            className = "ru.tinkoff.lections.sender.resource.sync.soap.GetMessageRequest")
    @WebMethod(action = "urn:GetMessage")
    @ResponseWrapper(
            localName = "getMessageResponse",
            targetNamespace = "sender-namespace",
            className = "ru.tinkoff.lections.sender.resource.sync.soap.GetMessageResponse")
    Message getMessage(@WebParam(name = "id", targetNamespace = "sender-namespace") int id);
}
