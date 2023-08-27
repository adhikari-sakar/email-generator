package com.machnet.domain.provider;

import static com.machnet.domain.email.EmailProviderType.SPARK;
import static com.machnet.domain.email.EmailStatus.SENT;
import static com.machnet.domain.email.Priority.TOP;
import static org.assertj.core.api.Assertions.assertThat;

import com.machnet.domain.template.SparkTemplate;
import com.machnet.domain.usecase.BaseTest;
import org.junit.jupiter.api.Test;

class SparkEmailProviderTest extends BaseTest {

    private final SparkEmailProvider provider = new SparkEmailProvider(emailService);

    @Test
    void testProperties() {
        assertThat(provider.getType()).isEqualTo(SPARK);
        assertThat(provider.getPriority()).isEqualTo(TOP);
        assertThat(provider.isSkipped()).isFalse();
        assertThat(provider.getTemplate()).isInstanceOf(SparkTemplate.class);
    }

    @Test
    void execute() {
        var sparkEmail = provider.execute();
        assertEmail(sparkEmail, "sparksender@test.com", "Spark email Template", "sparkreciever@test.com", SENT);
    }
}