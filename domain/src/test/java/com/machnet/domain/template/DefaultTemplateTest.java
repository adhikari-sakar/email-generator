package com.machnet.domain.template;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DefaultTemplateTest {

    @Test
    void template() {
        var template = new DefaultTemplate();
        assertThat(template).isNotNull();
        assertThat(template.getFrom().getEmailAddress()).isEqualTo("defaultsender@test.com");
        assertThat(template.getBody().getBody()).isEqualTo("Generic email Template");
        assertThat(template.getTo().getEmailAddress()).isEqualTo("defaultreceiver@test.com");

    }
}