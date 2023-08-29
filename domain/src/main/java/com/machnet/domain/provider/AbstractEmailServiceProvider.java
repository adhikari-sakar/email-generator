package com.machnet.domain.provider;

import com.machnet.domain.contracts.EmailServiceProvider;
import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailModel;
import com.machnet.domain.email.Priority;

import java.util.function.Function;

public abstract class AbstractEmailServiceProvider implements EmailServiceProvider {

    private final EmailService emailService;
    private boolean isSkipped = false;
    private Priority priority;

    protected AbstractEmailServiceProvider(EmailService emailService, Priority priority) {
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
    public EmailServiceProvider updatePriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public EmailServiceProvider skip() {
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

    @Override
    public EmailServiceProvider andThen(Function<EmailServiceProvider, EmailServiceProvider> providerFunction) {
        return providerFunction.apply(this);
    }
}
