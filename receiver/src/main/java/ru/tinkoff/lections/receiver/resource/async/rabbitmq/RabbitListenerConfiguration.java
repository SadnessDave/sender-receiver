package ru.tinkoff.lections.receiver.resource.async.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(RabbitListenerProperties.class)
@ConditionalOnProperty(prefix = "receiver.rabbit", name = "enabled", havingValue = "true")
public class RabbitListenerConfiguration {

    @Bean
    public ConnectionFactory connectionFactory(RabbitListenerProperties properties) {
        CachingConnectionFactory factory = new CachingConnectionFactory(properties.getGeneralProperties().getHostName());
        factory.setUsername(properties.getGeneralProperties().getUsername());
        factory.setPassword(properties.getGeneralProperties().getPassword());
        factory.setVirtualHost(properties.getGeneralProperties().getVirtualHost());
        return factory;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory, ObjectMapper objectMapper) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(new Jackson2JsonMessageConverter(objectMapper));

        return template;
    }

    @Bean
    public List<Queue> queues(RabbitListenerProperties properties) {
        return properties.getGeneralProperties().getQueues().stream()
                .map(n -> new Queue(n.getName()))
                .toList();
    }

    @Configuration
    @ConditionalOnProperty(prefix = "sender.rabbit", name = {"enabled", "exchange.direct"}, havingValue = "true")
    static class DirectConfiguration {

        @Bean
        public DirectExchange directExchange(RabbitListenerProperties properties) {
            return new DirectExchange(properties.getExchanges().get(0), true, false);
        }

        @Bean
        public List<Binding> bindings(RabbitListenerProperties properties, List<Queue> queues, DirectExchange exchange) {
            Map<String, String> propQueues = properties.getGeneralProperties().getQueues().stream()
                    .collect(Collectors.toMap(k -> k.getName(), v -> v.getRoutingKey()));

            return queues.stream()
                    .map(n -> BindingBuilder.bind(n)
                            .to(exchange)
                            .with(propQueues.get(n.getName())))
                    .toList();
        }
    }

    @Configuration
    @ConditionalOnProperty(prefix = "sender.rabbit", name = {"enabled", "exchange.fanout"}, havingValue = "true")
    static class FanoutConfiguration {

        @Bean
        public FanoutExchange fanoutExchange(RabbitListenerProperties properties) {
            return new FanoutExchange(properties.getExchanges().get(1), true, false);
        }
    }
}
