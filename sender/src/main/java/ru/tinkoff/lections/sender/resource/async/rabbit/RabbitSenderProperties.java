package ru.tinkoff.lections.sender.resource.async.rabbit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("sender.rabbit")
@ConditionalOnProperty(prefix = "sender.rabbit", name = "enabled", havingValue = "true")
public class RabbitSenderProperties {

    private GeneralProperties generalProperties;
    private List<String> exchanges;

    @Getter
    @Setter
    public static class GeneralProperties {

        private String hostName;
        private String username;
        private String password;
        private String virtualHost;

        private Map<String, String> queues;
    }
}
