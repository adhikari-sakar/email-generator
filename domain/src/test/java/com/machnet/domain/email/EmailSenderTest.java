package com.machnet.domain.email;

import com.machnet.domain.exception.DataNotFoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailSenderTest {

    @Test
    void givenNonEmptyEmailAddress_valid() {
        var sender = new EmailSender("sender@gmail.com");
        assertThat(sender).isNotNull();
    }

    @Test
    void givenEmptyEmailAddress_exception() {
        assertThrows(DataNotFoundException.class, () -> new EmailSender(""));
    }

    @Test
    void equalsAndHashCode() {
        var sender1 = new EmailSender("test@gmail.com");
        var sender2 = new EmailSender("test@gmail.com");
        assertThat(sender1).isEqualTo(sender2);
        assertThat(sender1.hashCode()).isEqualTo(sender2.hashCode());
    }
}