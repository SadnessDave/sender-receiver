package ru.tinkoff.lections.receiver.resource.async.rabbitmq;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties("receiver.rabbit")
public class RabbitListenerProperties {

    private GeneralProperties generalProperties;
    private List<String> exchanges;

    @Getter
    @Setter
    public static class GeneralProperties {

        private String hostName;
        private String username;
        private String password;
        private String virtualHost;

        private List<Queue> queues;

        @Getter
        @Setter
        public static class Queue {

            private String name;
            private String routingKey;
        }
    }
}
