package com.machnet.email.application.service;

import com.machnet.domain.contracts.EmailProvider;
import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.email.EmailModel;
import com.machnet.domain.provider.SparkEmailProvider;
import com.machnet.domain.usecase.EmailGenerateUseCase;
import com.machnet.email.application.mapper.EmailResponseMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Map;

import static com.machnet.domain.email.EmailProviderType.SPARK;
import static com.machnet.domain.email.EmailStatus.SENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class EmailGeneratorServiceTest {

    @Mock
    private EmailGenerateUseCase useCase;
    @Mock
    private EmailService emailService;

    private EmailGeneratorService service;

    private static EmailModel email() {
        return new EmailModel(new TestEmailTemplate(), SENT);
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        service = new EmailGeneratorService(new EmailResponseMapperImpl(), useCase,
                Map.of(SPARK, new SparkEmailProvider(emailService)));
    }

    @Test
    void generateEmail_whenEmailIsGenerated_thenResponseIsMapped() {
        when(useCase.generateEmail(any(EmailProvider.class), anyCollection())).thenReturn(email());
        var response = service.generateEmail(SPARK);
        assertThat(response).isNotNull();
        assertThat(response.getSender()).isEqualTo("test@sender.com");
        assertThat(response.getBody()).isEqualTo("test_body");
        assertThat(response.getReceiver()).isEqualTo("test@reciever.com");
    }

    @AfterEach
    void tearDown() {
        reset(useCase);
    }
}