package com.machnet.domain.provider;

import com.machnet.domain.contracts.EmailServiceProvider;
import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.template.SendGridTemplate;

import static com.machnet.domain.email.EmailProviderType.SENDGRID;
import static com.machnet.domain.email.Priority.HIGH;

public class SendGridEmailProvider extends AbstractEmailServiceProvider implements EmailServiceProvider {

    private final String senderEmail;
    private final String emailBody;
    private final String receiverEmail;

    public SendGridEmailProvider(EmailService emailService, String senderEmail, String emailBody,
                                 String receiverEmail) {
        super(emailService, HIGH);
        this.senderEmail = senderEmail;
        this.emailBody = emailBody;
        this.receiverEmail = receiverEmail;
    }

    @Override
    public EmailProviderType getType() {
        return SENDGRID;
    }

    @Override
    public EmailTemplate getTemplate() {
        return new SendGridTemplate(senderEmail, emailBody, receiverEmail);
    }
}
