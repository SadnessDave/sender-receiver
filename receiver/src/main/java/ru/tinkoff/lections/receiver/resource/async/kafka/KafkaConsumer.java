package ru.tinkoff.lections.receiver.resource.async.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.tinkoff.lections.receiver.domain.Message;
import ru.tinkoff.lections.receiver.resource.async.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "receiver.kafka", name = "enabled", havingValue = "true")
public class KafkaConsumer implements Consumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${receiver.kafka.topic}", groupId = "${receiver.kafka.group-id}")
    public void consume(String message) throws JsonProcessingException {
        Message result = objectMapper.readValue(message, Message.class);
        log.info(result.toString());
    }
}
