package com.machnet.domain.provider;

import com.machnet.domain.contracts.EmailServiceProvider;
import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.template.MailgunTemplate;

import static com.machnet.domain.email.EmailProviderType.MALIGUN;
import static com.machnet.domain.email.Priority.MEDIUM;

public class MailgunEmailProvider extends AbstractEmailServiceProvider implements EmailServiceProvider {

    public MailgunEmailProvider(EmailService emailService) {
        super(emailService, MEDIUM);
    }

    @Override
    public EmailProviderType getType() {
        return MALIGUN;
    }

    @Override
    public EmailTemplate getTemplate() {
        return new MailgunTemplate();
    }
}
