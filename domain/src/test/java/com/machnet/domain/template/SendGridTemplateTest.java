package com.machnet.domain.template;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SendGridTemplateTest {

    @Test
    void template() {
        var template = new SendGridTemplate("sendgridsender@test.com", "Sendgrid Email Template", "sendgridreciever@test.com");
        assertThat(template).isNotNull();
        assertThat(template.getFrom().getEmailAddress()).isEqualTo("sendgridsender@test.com");
        assertThat(template.getBody().getBody()).isEqualTo("Sendgrid Email Template");
        assertThat(template.getTo().getEmailAddress()).isEqualTo("sendgridreciever@test.com");

    }
}