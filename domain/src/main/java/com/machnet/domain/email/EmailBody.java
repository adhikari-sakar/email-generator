package com.machnet.domain.email;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@EqualsAndHashCode
public class EmailBody {

    String body;

    public EmailBody(String body) {
        this.body = body;
    }
}
