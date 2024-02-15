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
@ConditionalOnProperty(prefix = "sender.rabbit", name = {"enabled", "exchange.fanout"}, havingValue = "true")
public class RabbitFanoutSender implements Sender {

    @Value("${sender.rabbit.exchanges[1]}")
    private String exchange;

    private final RabbitTemplate rabbitTemplate;

    public void send(Message message) {
        rabbitTemplate.convertAndSend(exchange, "", message);
    }
}
