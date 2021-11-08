package com.mastery.java.task.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mastery.java.task")
public class AppConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }
}
