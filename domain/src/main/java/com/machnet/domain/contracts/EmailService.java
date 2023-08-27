package com.machnet.domain.contracts;

import static com.machnet.domain.email.EmailStatus.*;

import com.machnet.domain.email.EmailModel;
import com.machnet.domain.email.EmailStatus;
import lombok.NonNull;

public interface EmailService {

    default EmailModel send(@NonNull EmailTemplate template) {
        return new EmailModel(template, SENT);
    }

    default boolean isConnected() {
        return true;
    }
}
