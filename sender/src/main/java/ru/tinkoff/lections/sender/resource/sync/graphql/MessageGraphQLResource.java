package ru.tinkoff.lections.sender.resource.sync.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.lections.sender.domain.Message;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageGraphQLResource {

    private final MessageService messageService;

    @QueryMapping
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @QueryMapping
    public Message getMessage(@Argument int id) {
        return messageService.getMessageById(id);
    }

    @MutationMapping
    public Message sendMessage(@Argument String author, @Argument String content) {
        return messageService.addMessage(author, content);
    }
}
