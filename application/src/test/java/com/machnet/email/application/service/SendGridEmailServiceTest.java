package com.machnet.email.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.exception.EmailServiceException;
import com.machnet.email.application.component.SendGridApi;
import com.sendgrid.Response;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

class SendGridEmailServiceTest {

    @Mock
    private SendGridApi api;
    private SendGridEmailService service;

    @BeforeEach
    void setUp() {
        openMocks(this);
        service = new SendGridEmailService(api);
    }

    @Test
    void send_success() throws IOException {
        when(api.sendEmail(any(EmailTemplate.class))).thenReturn(new Response());
        var email = service.send(new TestEmailTemplate());
        assertThat(email).isNotNull();
    }

    @Test
    void send_failed() throws IOException {
        when(api.sendEmail(any(EmailTemplate.class))).thenThrow(new EmailServiceException("Failed to send email!!"));
        assertThrowsExactly(EmailServiceException.class, () -> service.send(new TestEmailTemplate()), "Failed to send email!!");
    }

    @Test
    void isConnected() {
        when(api.isConnected()).thenReturn(true);
        assertThat(service.isConnected()).isTrue();
    }
}