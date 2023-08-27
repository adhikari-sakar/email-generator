package com.machnet.domain.provider;

import com.machnet.domain.template.DefaultTemplate;
import com.machnet.domain.usecase.BaseTest;
import org.junit.jupiter.api.Test;

import static com.machnet.domain.email.EmailProviderType.DEFAULT;
import static com.machnet.domain.email.EmailStatus.SENT;
import static com.machnet.domain.email.Priority.LOW;
import static org.assertj.core.api.Assertions.assertThat;

class DefaultEmailProviderTest extends BaseTest {

    private final DefaultEmailProvider provider = new DefaultEmailProvider(emailService);

    @Test
    void testProperties() {
        assertThat(provider.getType()).isEqualTo(DEFAULT);
        assertThat(provider.getPriority()).isEqualTo(LOW);
        assertThat(provider.isSkipped()).isFalse();
        assertThat(provider.isUp()).isTrue();
        assertThat(provider.getTemplate()).isInstanceOf(DefaultTemplate.class);
    }

    @Test
    void execute() {
        var defaultEmail = provider.execute();
        assertEmail(defaultEmail, "defaultsender@test.com", "Generic email Template", "defaultreceiver@test.com", SENT);
    }
}