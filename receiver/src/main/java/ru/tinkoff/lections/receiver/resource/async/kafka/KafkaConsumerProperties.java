package ru.tinkoff.lections.receiver.resource.async.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("receiver.kafka")
public class KafkaConsumerProperties {

    private String brokers;
    private String groupId;
}
