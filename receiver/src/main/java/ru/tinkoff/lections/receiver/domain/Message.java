package ru.tinkoff.lections.receiver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private int id;
    private String author;
    private String content;
    private LocalDateTime lastModifiedDate;
}
