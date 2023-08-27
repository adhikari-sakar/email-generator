package com.machnet.email.application.component;

import com.machnet.email.application.exception.SendGridException;
import com.machnet.email.application.service.TestEmailTemplate;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class SendGridApiTest {

    @Mock
    private SendGrid sendGrid;
    private SendGridApi sendGridApi;

    @BeforeEach
    void setUp() {
        openMocks(this);
        sendGridApi = new SendGridApi(sendGrid);
    }

    @Test
    void sendEmail_success() throws IOException {
        when(sendGrid.api(any(Request.class))).thenReturn(response());
        var emailResponse = sendGridApi.sendEmail(new TestEmailTemplate());
        assertThat(emailResponse).isNotNull();
        assertThat(emailResponse.getBody()).isEqualTo("test");
        assertThat(emailResponse.getStatusCode()).isEqualTo(202);
    }

    @Test
    void sendEmail_failed() throws IOException {
        when(sendGrid.api(any(Request.class))).thenThrow(new RuntimeException("x"));
        assertThrowsExactly(SendGridException.class, () -> sendGridApi.sendEmail(new TestEmailTemplate()));
    }

    private Response response() {
        var response = new Response();
        response.setBody("test");
        response.setStatusCode(202);
        return response;
    }

    @Test
    void isConnected() {
        assertThat(sendGridApi.isConnected()).isTrue();
    }
}