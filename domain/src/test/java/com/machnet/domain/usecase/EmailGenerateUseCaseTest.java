package com.machnet.domain.usecase;

import static com.machnet.domain.email.EmailStatus.SENT;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.machnet.domain.contracts.EmailProvider;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.email.Priority;
import com.machnet.domain.exception.CommandNotFoundException;
import com.machnet.domain.provider.SparkEmailProvider;
import com.machnet.domain.template.DefaultTemplate;
import java.util.List;
import org.junit.jupiter.api.Test;

class EmailGenerateUseCaseTest extends BaseTest {

    private final EmailGenerateUseCase useCase = new EmailGenerateUseCase();

    private static EmailProviderMock defaultEmailProvider(boolean isUp) {
        return new EmailProviderMock(emailService, Priority.LOW, EmailProviderType.DEFAULT, new DefaultTemplate(),
            isUp);
    }

    @Test
    void generateEmail_givenProviderIsAvailable_thenSuccess() {
        EmailProvider emailProvider = defaultEmailProvider(true);
        var email = useCase.generateEmail(emailProvider, List.of());
        assertEmail(email, "defaultsender@test.com", "Generic email Template", "defaultreceiver@test.com", SENT);
    }

    @Test
    void generateEmail_givenProviderIsUnavailable_thenFallbackIsSuccess() {
        EmailProvider emailProvider = defaultEmailProvider(false);
        when(emailService.isConnected()).thenReturn(true);
        var email = useCase.generateEmail(emailProvider, mockProviders());
        assertEmail(email, "sparksender@test.com", "Spark email Template", "sparkreciever@test.com", SENT);
    }

    @Test
    void generateEmail_WhenAllProvidersAreDown_thenDefaultProviderIsInvoked() {
        when(emailService.isConnected()).thenReturn(false);
        assertThrows(CommandNotFoundException.class,
            () -> useCase.generateEmail(new SparkEmailProvider(emailService), mockProviders()));
    }

}