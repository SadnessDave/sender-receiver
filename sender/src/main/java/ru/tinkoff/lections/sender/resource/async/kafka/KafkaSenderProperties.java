package ru.tinkoff.lections.sender.resource.async.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("sender.kafka")
@ConditionalOnProperty(prefix = "sender.kafka", name = "enabled", havingValue = "true")
public class KafkaSenderProperties {

    private String brokers;
}
