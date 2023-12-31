package com.machnet.domain.provider;

import com.machnet.domain.template.MailgunTemplate;
import com.machnet.domain.usecase.BaseMockTest;
import org.junit.jupiter.api.Test;

import static com.machnet.domain.email.EmailProviderType.MALIGUN;
import static com.machnet.domain.email.EmailStatus.SENT;
import static com.machnet.domain.email.Priority.MEDIUM;
import static org.assertj.core.api.Assertions.assertThat;

class MailgunEmailProviderTest extends BaseMockTest {

    private final MailgunEmailProvider provider = new MailgunEmailProvider(emailService);

    @Test
    void testProperties() {
        assertThat(provider.getType()).isEqualTo(MALIGUN);
        assertThat(provider.getPriority()).isEqualTo(MEDIUM);
        assertThat(provider.isSkipped()).isFalse();
        assertThat(provider.getTemplate()).isInstanceOf(MailgunTemplate.class);
    }

    @Test
    void execute() {
        var mailGunEmail = provider.execute();
        assertEmail(mailGunEmail, "mailgunsender@test.com", "Mailgun Email Template", "mailgunreciever@test.com", SENT);
    }
}