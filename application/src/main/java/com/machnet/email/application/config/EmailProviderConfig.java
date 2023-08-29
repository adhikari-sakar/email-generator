package com.machnet.email.application.config;

import com.machnet.domain.contracts.EmailServiceProvider;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.provider.DefaultEmailProvider;
import com.machnet.domain.provider.MailgunEmailProvider;
import com.machnet.domain.provider.SparkEmailProvider;
import com.machnet.domain.usecase.EmailGenerateUseCase;
import com.machnet.email.application.service.DefaultEmailService;
import com.machnet.email.application.service.MailgunEmailService;
import com.machnet.email.application.service.SparkEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Configuration
public class EmailProviderConfig {

    //strategy patten to inject provider and services
    @Bean
    EmailGenerateUseCase emailGenerateUseCase() {
        return new EmailGenerateUseCase();
    }

    @Bean
    EmailServiceProvider mailGunEmailProvider() {
        return new MailgunEmailProvider(new MailgunEmailService());
    }

    @Bean
    EmailServiceProvider sparkEmailProvider() {
        return new SparkEmailProvider(new SparkEmailService());
    }

    @Bean
    EmailServiceProvider defaultEmailProvider() {
        return new DefaultEmailProvider(new DefaultEmailService());
    }


    //fill map with provider types and concrete providers
    @Bean
    Map<EmailProviderType, EmailServiceProvider> emailProviders(List<EmailServiceProvider> emailProviders) {
        return emailProviders.stream().collect(toMap(EmailServiceProvider::getType, Function.identity()));
    }
}
