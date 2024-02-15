package ru.tinkoff.lections.receiver.resource.sync.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.tinkoff.lections.receiver.domain.FormattedMessage;
import ru.tinkoff.lections.receiver.domain.Message;

@RestController
@RequestMapping("/api/v1/sync")
@RequiredArgsConstructor
public class SyncRestMessageResource {

    private final MessageFeignClient messageFeignClient;

    @PostMapping("/rest-template/message/{id}/format")
    public FormattedMessage getFormattedMessages(@PathVariable int id) {
        Message message = new RestTemplate()
                .getForEntity("http://localhost:8080/api/v1/sync/message/{id}", Message.class, id)
                .getBody();

        return new FormattedMessage(message.getId(), message.getId() + ". " + message.getContent());
    }

    @PostMapping("/feign-client/message/{id}/format")
    public FormattedMessage getFormattedMessage(@PathVariable int id) {
        Message message = messageFeignClient.getMessage(id);

        return new FormattedMessage(message.getId(), message.getId() + ". " + message.getContent());
    }

    @FeignClient(name = "message-sender", url = "http://localhost:8080/api/v1/sync")
    public interface MessageFeignClient {

        @GetMapping("/message/{id}")
        Message getMessage(@PathVariable int id);
    }
}
