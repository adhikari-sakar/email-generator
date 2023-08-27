package com.machnet.email.application.service;

import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailBody;
import com.machnet.domain.email.EmailReceiver;
import com.machnet.domain.email.EmailSender;

public class TestEmailTemplate implements EmailTemplate {

    @Override
    public EmailSender getFrom() {
        return new EmailSender("test@sender.com");
    }

    @Override
    public EmailBody getBody() {
        return new EmailBody("test_body");
    }

    @Override
    public EmailReceiver getTo() {
        return new EmailReceiver("test@reciever.com");
    }
}
