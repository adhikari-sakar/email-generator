package com.machnet.domain.email;

import com.machnet.domain.exception.DataNotFoundException;
import com.machnet.shared.utils.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class EmailSender {

    String emailAddress;

    public EmailSender(String emailAddress) {
        // email validation logic could be added
        if (StringUtils.isEmpty(emailAddress)) {
            throw new DataNotFoundException("Email address not found");
        }
        this.emailAddress = emailAddress;
    }
}
