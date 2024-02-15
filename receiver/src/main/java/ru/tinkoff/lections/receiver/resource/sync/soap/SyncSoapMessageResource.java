package ru.tinkoff.lections.receiver.resource.sync.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.lections.receiver.domain.FormattedMessage;
import ru.tinkoff.lections.receiver.resource.sync.soap.gen.GetMessageResponse;
import ru.tinkoff.lections.receiver.resource.sync.soap.gen.Message;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sync")
public class SyncSoapMessageResource {

    private final SenderClient senderClient;

    @PostMapping("/soap/message/{id}/format")
    public FormattedMessage getFormattedMessages(@PathVariable int id) {
        GetMessageResponse response = senderClient.getMessage(id);

        Message message = response.getMessage();
        return new FormattedMessage(message.getId(), message.getId() + ". " + message.getContent());
    }
}
