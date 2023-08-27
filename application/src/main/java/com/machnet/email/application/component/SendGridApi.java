package com.machnet.email.application.component;

import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.email.application.exception.SendGridException;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SendGridApi {

    private final SendGrid sg;

    public SendGridApi(SendGrid sg) {
        this.sg = sg;
    }

    public Response sendEmail(EmailTemplate template) throws IOException {
        var mail = buildMail(template);
        var request = buildRequest(mail);
        try {
            Response response = sg.api(request);
            log.info(response.getBody());
            return response;
        } catch (Exception ex) {
            log.error(ex);
            throw new SendGridException("Error while sending mail " + ex.getMessage());
        }
    }

    private Mail buildMail(EmailTemplate template) {
        var from = new Email(template.getFrom().getEmailAddress());
        String subject = template.getSubject().getSubject();
        var to = new Email(template.getTo().getEmailAddress());
        var content = new Content("text/plain", template.getBody().getBody());
        return new Mail(from, subject, to, content);
    }

    private Request buildRequest(Mail mail) throws IOException {
        var request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        return request;
    }

    public boolean isConnected() {
        return sg != null;
    }
}
