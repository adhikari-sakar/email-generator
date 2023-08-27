package com.machnet.domain.template;

import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailBody;
import com.machnet.domain.email.EmailReceiver;
import com.machnet.domain.email.EmailSender;

public class SparkTemplate implements EmailTemplate {

    @Override
    public EmailSender getFrom() {
        return new EmailSender("sparksender@test.com");
    }

    @Override
    public EmailBody getBody() {
        return new EmailBody("Spark email Template");
    }

    @Override
    public EmailReceiver getTo() {
        return new EmailReceiver("sparkreciever@test.com");
    }

}
