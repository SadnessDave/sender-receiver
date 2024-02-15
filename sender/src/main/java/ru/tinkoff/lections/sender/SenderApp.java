package ru.tinkoff.lections.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SenderApp {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(SenderApp.class, args);
    }
}