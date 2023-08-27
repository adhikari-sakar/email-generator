package com.machnet.domain.template;

import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailBody;

public class DefaultTemplate implements EmailTemplate {

    @Override
    public EmailBody getBody() {
        return new EmailBody("Generic email Template");
    }

}
