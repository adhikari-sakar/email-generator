package com.machnet.domain.email;

import com.machnet.domain.contracts.EmailTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@EqualsAndHashCode
public class EmailModel {

    EmailSubject subject;
    EmailSender sender;
    EmailBody body;
    EmailReceiver receiver;
    EmailStatus status;

    public EmailModel(EmailTemplate template, EmailStatus status) {
        this.subject = template.getSubject();
        this.sender = template.getFrom();
        this.body = template.getBody();
        this.receiver = template.getTo();
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmailModel{" +
                "subject=" + subject +
                ", sender=" + sender +
                ", body=" + body +
                ", receiver=" + receiver +
                ", status=" + status.name() +
                '}';
    }
}
