package com.machnet.domain.provider;

import com.machnet.domain.contracts.EmailProvider;
import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailModel;
import com.machnet.domain.email.Priority;

public abstract class AbstractEmailProvider implements EmailProvider {

    private final EmailService emailService;
    private boolean isSkipped = false;
    private Priority priority;

    protected AbstractEmailProvider(EmailService emailService, Priority priority) {
        this.emailService = emailService;
        this.priority = priority;
    }

    //template pattern to get concrete email template
    public abstract EmailTemplate getTemplate();

    @Override
    public EmailModel execute() {
        return emailService.send(getTemplate());
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public EmailProvider updatePriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public EmailProvider skip() {
        this.isSkipped = true;
        return this;
    }

    @Override
    public boolean isSkipped() {
        return this.isSkipped;
    }

    @Override
    public EmailService getService() {
        return this.emailService;
    }
}
