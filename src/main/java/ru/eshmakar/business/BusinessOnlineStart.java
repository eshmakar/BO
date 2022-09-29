package ru.eshmakar.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BusinessOnlineStart {

    public static void main(String[] args) {
        SpringApplication.run(BusinessOnlineStart.class, args);

    }
}