package com.machnet.domain.contracts;

import com.machnet.domain.email.*;

public interface EmailTemplate {

    default EmailSender getFrom() {
        return new DefaultEmailSender();
    }

    default EmailSubject getSubject() {
        return new EmailSubject("Test subject");
    }

    EmailBody getBody();

    default EmailReceiver getTo() {
        return new DefaultEmailReceiver();
    }
}
