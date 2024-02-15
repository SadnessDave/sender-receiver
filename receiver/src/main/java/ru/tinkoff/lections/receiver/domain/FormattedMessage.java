package ru.tinkoff.lections.receiver.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FormattedMessage {

    private final int id;
    private final String formattedContent;
}
