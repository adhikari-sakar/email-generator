package com.machnet.domain.contracts;

import com.machnet.domain.email.DefaultEmailReceiver;
import com.machnet.domain.email.DefaultEmailSender;
import com.machnet.domain.email.EmailBody;
import com.machnet.domain.email.EmailReceiver;
import com.machnet.domain.email.EmailSender;
import com.machnet.domain.email.EmailSubject;

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
