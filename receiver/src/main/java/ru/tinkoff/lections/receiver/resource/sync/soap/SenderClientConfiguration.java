package ru.tinkoff.lections.receiver.resource.sync.soap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SenderClientConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.tinkoff.lections.receiver.resource.sync.soap.gen");
        return marshaller;
    }

    @Bean
    public SenderClient senderClient(Jaxb2Marshaller marshaller) {
        SenderClient client = new SenderClient();
        client.setDefaultUri("http://localhost:8080/services/sender");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
