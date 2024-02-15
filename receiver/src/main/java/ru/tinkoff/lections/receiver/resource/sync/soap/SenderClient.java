package ru.tinkoff.lections.receiver.resource.sync.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.tinkoff.lections.receiver.resource.sync.soap.gen.GetMessageResponse;
import ru.tinkoff.lections.receiver.resource.sync.soap.gen.GetRequestMessage;
import ru.tinkoff.lections.receiver.resource.sync.soap.gen.ObjectFactory;

import javax.xml.bind.JAXBElement;

public class SenderClient extends WebServiceGatewaySupport {

    public GetMessageResponse getMessage(int id) {
        GetRequestMessage request = new GetRequestMessage();
        request.setId(id);

        JAXBElement<GetMessageResponse> response = (JAXBElement<GetMessageResponse>) getWebServiceTemplate()
                .marshalSendAndReceive(new ObjectFactory().createGetRequestMessage(request));

        return response.getValue();
    }
}
