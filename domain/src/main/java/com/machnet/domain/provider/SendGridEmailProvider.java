package com.machnet.domain.provider;

import static com.machnet.domain.email.EmailProviderType.SENDGRID;
import static com.machnet.domain.email.Priority.HIGH;

import com.machnet.domain.contracts.EmailProvider;
import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.template.SendGridTemplate;

public class SendGridEmailProvider extends AbstractEmailProvider implements EmailProvider {

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
