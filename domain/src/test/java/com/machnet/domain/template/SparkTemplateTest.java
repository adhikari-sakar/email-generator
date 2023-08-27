package com.machnet.domain.template;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SparkTemplateTest {

    @Test
    void template() {
        var template = new SparkTemplate();
        assertThat(template).isNotNull();
        assertThat(template.getFrom().getEmailAddress()).isEqualTo("sparksender@test.com");
        assertThat(template.getBody().getBody()).isEqualTo("Spark email Template");
        assertThat(template.getTo().getEmailAddress()).isEqualTo("sparkreciever@test.com");

    }
}