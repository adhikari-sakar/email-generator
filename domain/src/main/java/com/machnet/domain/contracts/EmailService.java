package com.machnet.domain.contracts;

import com.machnet.domain.email.EmailModel;
import lombok.NonNull;

import static com.machnet.domain.email.EmailStatus.SENT;

public interface EmailService {

    default EmailModel send(@NonNull EmailTemplate template) {
        return new EmailModel(template, SENT);
    }

    default boolean isConnected() {
        return true;
    }
}
