package com.machnet.domain.template;

import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailBody;
import com.machnet.domain.email.EmailReceiver;
import com.machnet.domain.email.EmailSender;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SendGridTemplate implements EmailTemplate {

    private final String senderEmail;
    private final String emailBody;
    private final String receiverEmail;

    @Override
    public EmailSender getFrom() {
        return new EmailSender(senderEmail);
    }

    @Override
    public EmailBody getBody() {
        return new EmailBody(emailBody);
    }

    @Override
    public EmailReceiver getTo() {
        return new EmailReceiver(receiverEmail);
    }
}
