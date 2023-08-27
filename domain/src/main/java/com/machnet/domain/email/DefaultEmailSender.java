package com.machnet.domain.email;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class DefaultEmailSender extends EmailSender {

    public DefaultEmailSender() {
        super("defaultsender@test.com");
    }
}
