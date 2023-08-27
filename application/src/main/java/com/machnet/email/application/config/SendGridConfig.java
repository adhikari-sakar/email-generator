package com.machnet.email.application.config;

import com.machnet.domain.contracts.EmailProvider;
import com.machnet.domain.provider.SendGridEmailProvider;
import com.machnet.email.application.component.SendGridApi;
import com.machnet.email.application.service.SendGridEmailService;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfig {

    @Value("${send.grid.email.sender}")
    private String sender;
    @Value("${send.grid.email.body}")
    private String body;
    @Value("${send.grid.email.receiver}")
    private String receiver;
    @Value("${send.grid.api.key}")
    private String sendGridApiKey;

    @Bean
    EmailProvider sendGridEmailProvider(SendGridApi sendGridApi) {
        return new SendGridEmailProvider(new SendGridEmailService(sendGridApi), sender, body, receiver);
    }

    @Bean
    SendGrid sendGrid() {
        return new SendGrid(sendGridApiKey);
    }
}
