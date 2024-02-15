package ru.tinkoff.lections.sender.resource.async.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties(RabbitSenderProperties.class)
@ConditionalOnProperty(prefix = "sender.rabbit", name = "enabled", havingValue = "true")
public class RabbitSenderConfiguration {

    @Bean
    public ConnectionFactory connectionFactory(RabbitSenderProperties properties) {
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
    public List<Queue> queues(RabbitSenderProperties properties) {
        return properties.getGeneralProperties().getQueues().keySet().stream()
                .map(Queue::new)
                .toList();
    }

    @Configuration
    @ConditionalOnProperty(prefix = "sender.rabbit", name = {"enabled", "exchange.direct"}, havingValue = "true")
    static class DirectConfiguration {

        @Bean
        public DirectExchange directExchange(RabbitSenderProperties properties) {
            return new DirectExchange(properties.getExchanges().get(0), true, false);
        }

        @Bean
        public List<Binding> directBindings(RabbitSenderProperties properties, List<Queue> queues, DirectExchange exchange) {
            return queues.stream()
                    .map(n -> BindingBuilder.bind(n)
                            .to(exchange)
                            .with(properties.getGeneralProperties().getQueues().get(n.getName())))
                    .toList();
        }
    }

    @Configuration
    @ConditionalOnProperty(prefix = "sender.rabbit", name = {"enabled", "exchange.fanout"}, havingValue = "true")
    static class FanoutConfiguration {

        @Bean
        public FanoutExchange fanoutExchange(RabbitSenderProperties properties) {
            return new FanoutExchange(properties.getExchanges().get(1), true, false);
        }

        @Bean
        public Declarables fanoutBindings(FanoutExchange exchange, List<Queue> queues) {
            List<Binding> bindings = queues.stream()
                    .map(n -> BindingBuilder.bind(n).to(exchange))
                    .toList();

            return new Declarables(
                    bindings.get(0),
                    bindings.get(1)
            );
        }
    }
}
