package com.machnet.domain.provider;

import static com.machnet.domain.email.EmailProviderType.MALIGUN;
import static com.machnet.domain.email.Priority.MEDIUM;

import com.machnet.domain.contracts.EmailProvider;
import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.template.MailgunTemplate;

public class MailgunEmailProvider extends AbstractEmailProvider implements EmailProvider {

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
