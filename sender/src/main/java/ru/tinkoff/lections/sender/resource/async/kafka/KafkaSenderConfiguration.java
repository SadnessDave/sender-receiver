package ru.tinkoff.lections.sender.resource.async.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.tinkoff.lections.sender.domain.Message;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaSenderProperties.class)
@ConditionalOnProperty(prefix = "sender.kafka", name = "enabled", havingValue = "true")
public class KafkaSenderConfiguration {

    @Bean
    public KafkaTemplate<String, Message> producerTemplate(KafkaSenderProperties properties, ObjectMapper objectMapper) {

        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBrokers());

        var factory = new DefaultKafkaProducerFactory<>(configProperties,
                StringSerializer::new, () -> new JsonSerializer<Message>(objectMapper));

        return new KafkaTemplate<>(factory);
    }
}
