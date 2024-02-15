package ru.tinkoff.lections.sender.resource.async;

import ru.tinkoff.lections.sender.domain.Message;

public interface Sender {

    void send(Message message);
}
