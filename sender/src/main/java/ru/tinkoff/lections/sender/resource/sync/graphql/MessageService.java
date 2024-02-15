package ru.tinkoff.lections.sender.resource.sync.graphql;

import org.springframework.stereotype.Service;
import ru.tinkoff.lections.sender.domain.Message;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    private final Map<Integer, Message> messages = new HashMap<>();

    public MessageService() {
        for (int i = 0; i < 10; i++) {
            messages.put(i, new Message(
                    i,
                    String.format("%s. Author", i + 1),
                    String.format("%s. Greeting message!", i + 1),
                    LocalDateTime.now()
            ));
        }
    }

    public List<Message> getAllMessages() {
        return messages.values().stream().toList();
    }

    public Message getMessageById(int id) {
        return messages.get(id);
    }

    public Message addMessage(String author, String content) {
        int id = messages.size() + 1;
        Message message = new Message(id, author, content, LocalDateTime.now());

        messages.put(id, message);
        return message;
    }
}
