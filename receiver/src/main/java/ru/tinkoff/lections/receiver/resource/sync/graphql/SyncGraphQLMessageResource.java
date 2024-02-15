package ru.tinkoff.lections.receiver.resource.sync.graphql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.lections.receiver.domain.FormattedMessage;
import ru.tinkoff.lections.receiver.domain.Message;

@Slf4j
@RestController
@RequestMapping("/api/v1/sync")
public class SyncGraphQLMessageResource {

    @PostMapping("/graphql/message/{id}/format")
    public FormattedMessage getFormattedMessages(@PathVariable int id) {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080/graphql")
                .build();

        HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder(client)
                .build();

        String document = """
                query {
                    getMessage(id: %s) {
                        author
                        content
                        lastModifiedDate
                    }
                }
                """.formatted(id);

        Message message = graphQlClient.document(document)
                .retrieve("getMessage")
                .toEntity(Message.class)
                .block();

        log.info(message.toString());
        return new FormattedMessage(message.getId(), message.getId() + ". " + message.getContent());
    }
}
