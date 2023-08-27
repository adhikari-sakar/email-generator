package com.machnet.domain.email;

import com.machnet.domain.exception.DataNotFoundException;
import com.machnet.shared.utils.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class EmailReceiver {

    String emailAddress;

    public EmailReceiver(String emailAddress) {
        if (StringUtils.isEmpty(emailAddress)) {
            throw new DataNotFoundException("Receiver email address not found");
        }
        this.emailAddress = emailAddress;
    }
}
