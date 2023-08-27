package com.machnet.domain.template;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MailgunTemplateTest {

    @Test
    void template() {
        var template = new MailgunTemplate();
        assertThat(template).isNotNull();
        assertThat(template.getFrom().getEmailAddress()).isEqualTo("mailgunsender@test.com");
        assertThat(template.getBody().getBody()).isEqualTo("Mailgun Email Template");
        assertThat(template.getTo().getEmailAddress()).isEqualTo("mailgunreciever@test.com");

    }
}