package ru.tinkoff.lections.receiver.resource.async;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Consumer {

    void consume(String message) throws JsonProcessingException;
}
