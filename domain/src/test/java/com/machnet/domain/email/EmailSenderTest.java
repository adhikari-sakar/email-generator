package com.machnet.domain.email;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.machnet.domain.exception.DataNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailSenderTest {

    @Test
    void givenNonEmptyEmailAddress_valid() {
        var sender = new EmailSender("sender@gmail.com");
        assertThat(sender).isNotNull();
    }

    @Test
    void givenEmptyEmailAddress_exception() {
        assertThrows(DataNotFoundException.class,() -> new EmailSender(""));
    }

    @Test
    void equalsAndHashCode(){
        var sender1 = new EmailSender("test@gmail.com");
        var sender2 = new EmailSender("test@gmail.com");
        assertThat(sender1).isEqualTo(sender2);
        assertThat(sender1.hashCode()).isEqualTo(sender2.hashCode());
    }
}