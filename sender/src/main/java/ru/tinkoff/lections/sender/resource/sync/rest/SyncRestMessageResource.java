package ru.tinkoff.lections.sender.resource.sync.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.lections.sender.domain.Message;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/sync")
public class SyncRestMessageResource {

    @GetMapping("/message/{id}")
    public Message getMessage(@PathVariable int id) {
        return new Message(id, "author", "Greeting message!", LocalDateTime.now());
    }
}
