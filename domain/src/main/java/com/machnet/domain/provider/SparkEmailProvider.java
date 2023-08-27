package com.machnet.domain.provider;

import static com.machnet.domain.email.EmailProviderType.SPARK;
import static com.machnet.domain.email.Priority.TOP;

import com.machnet.domain.contracts.EmailProvider;
import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.template.SparkTemplate;

public class SparkEmailProvider extends AbstractEmailProvider implements EmailProvider {

    public SparkEmailProvider(EmailService emailService) {
        super(emailService, TOP);
    }

    @Override
    public EmailProviderType getType() {
        return SPARK;
    }

    @Override
    public EmailTemplate getTemplate() {
        return new SparkTemplate();
    }
}
