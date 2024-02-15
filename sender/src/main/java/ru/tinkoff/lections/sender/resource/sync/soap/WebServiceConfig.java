package ru.tinkoff.lections.sender.resource.sync.soap;

import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
@RequiredArgsConstructor
public class WebServiceConfig {

    private final Bus bus;

    @Bean
    public Endpoint getMessageEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new SyncSoapSenderServiceImpl());
        endpoint.publish("/sender");

        return endpoint;
    }
}
