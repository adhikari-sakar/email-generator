package com.machnet.domain.usecase;

import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.email.Priority;
import com.machnet.domain.provider.AbstractEmailProvider;

public class EmailProviderMock extends AbstractEmailProvider {

    private final EmailProviderType type;
    private final EmailTemplate template;
    private final boolean isUp;

    public EmailProviderMock(EmailService emailService, Priority priority, EmailProviderType type,
                             EmailTemplate template, boolean isUp) {
        super(emailService, priority);
        this.type = type;
        this.template = template;
        this.isUp = isUp;
    }

    @Override
    public EmailProviderType getType() {
        return type;
    }

    @Override
    public EmailTemplate getTemplate() {
        return template;
    }

    @Override
    public Boolean isUp() {
        return isUp;
    }

    @Override
    public boolean isSkipped() {
        return !isUp;
    }
}
