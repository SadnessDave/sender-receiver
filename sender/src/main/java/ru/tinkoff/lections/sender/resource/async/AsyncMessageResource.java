package ru.tinkoff.lections.sender.resource.async;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.lections.sender.domain.Message;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/async")
@ConditionalOnProperty(name = "async", havingValue = "true")
public class AsyncMessageResource {

    private final Sender sender;

    @PostMapping("/send")
    public void sendMessage(@RequestBody Message message) {
        sender.send(message);
    }
}
