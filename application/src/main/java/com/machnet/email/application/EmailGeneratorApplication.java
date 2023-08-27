package com.machnet.email.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class EmailGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailGeneratorApplication.class, args);
    }

}
