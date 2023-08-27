package com.machnet.email.application.config;

import com.machnet.domain.contracts.EmailProvider;
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
    EmailProvider mailGunEmailProvider() {
        return new MailgunEmailProvider(new MailgunEmailService());
    }

    @Bean
    EmailProvider sparkEmailProvider() {
        return new SparkEmailProvider(new SparkEmailService());
    }

    @Bean
    EmailProvider defaultEmailProvider() {
        return new DefaultEmailProvider(new DefaultEmailService());
    }


    //fill map with provider types and concrete providers
    @Bean
    Map<EmailProviderType, EmailProvider> emailProviders(List<EmailProvider> emailProviders) {
        return emailProviders.stream().collect(toMap(EmailProvider::getType, Function.identity()));
    }
}
