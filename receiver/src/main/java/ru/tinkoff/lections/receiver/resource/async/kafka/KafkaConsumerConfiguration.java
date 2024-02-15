package ru.tinkoff.lections.receiver.resource.async.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaConsumerProperties.class)
@ConditionalOnProperty(prefix = "receiver.kafka", name = "enabled", havingValue = "true")
public class KafkaConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, String> consumerFactory(KafkaConsumerProperties properties) {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBrokers());
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getGroupId());

        return new DefaultKafkaConsumerFactory<>(configProperties, StringDeserializer::new, StringDeserializer::new);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            KafkaConsumerProperties properties) {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(properties));
        return factory;
    }
}
