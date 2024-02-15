package ru.tinkoff.lections.sender.resource.async.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.tinkoff.lections.sender.domain.Message;
import ru.tinkoff.lections.sender.resource.async.Sender;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "sender.kafka", name = "enabled", havingValue = "true")
public class KafkaSender implements Sender {

    @Value("${sender.kafka.topic:}")
    private String topic;

    private final KafkaTemplate<String, Message> kafkaTemplate;

    public void send(Message message) {
        kafkaTemplate.send(topic, message);
    }
}
