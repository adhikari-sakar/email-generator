package com.machnet.domain.provider;

import com.machnet.domain.template.SendGridTemplate;
import com.machnet.domain.usecase.BaseTest;
import org.junit.jupiter.api.Test;

import static com.machnet.domain.email.EmailProviderType.SENDGRID;
import static com.machnet.domain.email.EmailStatus.SENT;
import static com.machnet.domain.email.Priority.HIGH;
import static org.assertj.core.api.Assertions.assertThat;

class SendGridEmailProviderTest extends BaseTest {

    private final SendGridEmailProvider provider = new SendGridEmailProvider(emailService, "sendgridsender@test.com",
            "SendGrid email Template", "sendgridreciever@test.com");

    @Test
    void testProperties() {
        assertThat(provider.getType()).isEqualTo(SENDGRID);
        assertThat(provider.getPriority()).isEqualTo(HIGH);
        assertThat(provider.isSkipped()).isFalse();
        assertThat(provider.getTemplate()).isInstanceOf(SendGridTemplate.class);
    }

    @Test
    void execute() {
        var sendGridEmail = provider.execute();
        assertEmail(sendGridEmail, "sendgridsender@test.com", "SendGrid email Template", "sendgridreciever@test.com", SENT);
    }
}