package ru.tinkoff.lections.receiver.resource.async.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.tinkoff.lections.receiver.domain.Message;
import ru.tinkoff.lections.receiver.resource.async.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "receiver.rabbit", name = "enabled", havingValue = "true")
public class FirstRabbitConsumer implements Consumer {

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${receiver.rabbit.general-properties.queues[0].name}")
    public void consume(String message) throws JsonProcessingException {
        Message result = objectMapper.readValue(message, Message.class);
        log.info(result.toString());
    }
}
