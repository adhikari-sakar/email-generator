package com.machnet.email.application.service;

import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailModel;
import com.machnet.domain.template.SendGridTemplate;
import com.machnet.email.application.component.SendGridApi;
import com.machnet.email.application.exception.SendGridException;
import com.sendgrid.Response;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.machnet.domain.email.EmailStatus.FAILED;
import static com.machnet.domain.email.EmailStatus.SENT;

@Service
@Log4j2
@AllArgsConstructor
public class SendGridEmailService implements EmailService {

    // SendGrid specific implementation
    private final SendGridApi api;

    @Override
    public EmailModel send(EmailTemplate template) {
        try {
            Response response = api.sendEmail(template);
            return new EmailModel(new SendGridTemplate(template.getFrom().getEmailAddress(), response.getBody(),
                    template.getTo().getEmailAddress()), SENT);
        } catch (SendGridException | IOException ex) {
            return new EmailModel(template, FAILED);
        }
    }

    @Override
    public boolean isConnected() {
        return api.isConnected();
    }
}
