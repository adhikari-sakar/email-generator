package com.machnet.domain.email;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class DefaultEmailReceiver extends EmailReceiver {

    public DefaultEmailReceiver() {
        super("defaultreceiver@test.com");
    }
}
