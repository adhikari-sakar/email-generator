package com.machnet.domain.template;

import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailBody;
import com.machnet.domain.email.EmailReceiver;
import com.machnet.domain.email.EmailSender;

public class MailgunTemplate implements EmailTemplate {

    @Override
    public EmailSender getFrom() {
        return new EmailSender("mailgunsender@test.com");
    }

    @Override
    public EmailBody getBody() {
        return new EmailBody("Mailgun Email Template");
    }

    @Override
    public EmailReceiver getTo() {
        return new EmailReceiver("mailgunreciever@test.com");
    }
}
