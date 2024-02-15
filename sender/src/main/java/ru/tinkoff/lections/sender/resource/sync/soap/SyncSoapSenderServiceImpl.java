package ru.tinkoff.lections.sender.resource.sync.soap;

import ru.tinkoff.lections.sender.domain.Message;

import javax.jws.WebService;
import java.time.LocalDateTime;

@WebService(
        serviceName = "sender",
        portName = "SenderPort",
        targetNamespace = "sender-namespace",
        endpointInterface = "ru.tinkoff.lections.sender.resource.sync.soap.SyncSoapSenderService")
public class SyncSoapSenderServiceImpl implements SyncSoapSenderService {

    @Override
    public Message getMessage(int id) {
        return new Message(id, "author", "Greeting message!", LocalDateTime.now());
    }
}
