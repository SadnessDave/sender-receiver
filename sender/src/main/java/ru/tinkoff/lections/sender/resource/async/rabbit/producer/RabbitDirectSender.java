package ru.tinkoff.lections.sender.resource.async.rabbit.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.tinkoff.lections.sender.domain.Message;
import ru.tinkoff.lections.sender.resource.async.Sender;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "sender.rabbit", name = {"enabled", "exchange.direct"}, havingValue = "true")
public class RabbitDirectSender implements Sender {

    @Value("${sender.rabbit.exchanges[0]}")
    private String exchange;

    @Value("${sender.rabbit.general-properties.queues.sender_receiver_queue_1}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void send(Message message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
