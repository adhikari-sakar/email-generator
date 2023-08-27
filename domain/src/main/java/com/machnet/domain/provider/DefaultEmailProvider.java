package com.machnet.domain.provider;

import static com.machnet.domain.email.EmailProviderType.DEFAULT;
import static com.machnet.domain.email.Priority.LOW;

import com.machnet.domain.contracts.EmailProvider;
import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.template.DefaultTemplate;

public class DefaultEmailProvider extends AbstractEmailProvider implements EmailProvider {

    public DefaultEmailProvider(EmailService emailService) {
        super(emailService, LOW);
    }

    @Override
    public EmailProviderType getType() {
        return DEFAULT;
    }

    @Override
    public Boolean isUp() {
        return true;
    }

    @Override
    public EmailTemplate getTemplate() {
        return new DefaultTemplate();
    }
}