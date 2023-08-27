package com.machnet.domain.usecase;

import com.machnet.domain.contracts.EmailProvider;
import com.machnet.domain.contracts.EmailService;
import com.machnet.domain.email.EmailModel;
import com.machnet.domain.email.EmailStatus;
import com.machnet.domain.template.DefaultTemplate;
import com.machnet.domain.template.MailgunTemplate;
import com.machnet.domain.template.SendGridTemplate;
import com.machnet.domain.template.SparkTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import java.util.List;

import static com.machnet.domain.email.EmailProviderType.*;
import static com.machnet.domain.email.EmailStatus.SENT;
import static com.machnet.domain.email.Priority.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class BaseTest {

    @Mock
    protected static EmailService emailService;

    public static void assertEmail(EmailModel email, String senderAddress, String emailBody, String receiverAddress, EmailStatus status) {
        assertThat(email).isNotNull();
        assertThat(email.getSender()).isNotNull();
        assertThat(email.getBody()).isNotNull();
        assertThat(email.getReceiver()).isNotNull();
        assertThat(email.getSender().getEmailAddress()).isEqualTo(senderAddress);
        assertThat(email.getBody().getBody()).isEqualTo(emailBody);
        assertThat(email.getReceiver().getEmailAddress()).isEqualTo(receiverAddress);
    }

    private static EmailModel sparkEmail() {
        return new EmailModel(new SparkTemplate(), SENT);
    }

    private static EmailModel sendGridEmail() {
        return new EmailModel(sendGridTemplate(), SENT);
    }

    private static EmailModel mailGunEmail() {
        return new EmailModel(new MailgunTemplate(), SENT);
    }

    private static EmailModel defaultEmail() {
        return new EmailModel(new DefaultTemplate(), SENT);
    }

    private static SendGridTemplate sendGridTemplate() {
        return new SendGridTemplate("sendgridsender@test.com", "SendGrid email Template", "sendgridreciever@test.com");
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        when(emailService.send(ArgumentMatchers.any(SparkTemplate.class))).thenReturn(sparkEmail());
        when(emailService.send(ArgumentMatchers.any(SendGridTemplate.class))).thenReturn(sendGridEmail());
        when(emailService.send(ArgumentMatchers.any(MailgunTemplate.class))).thenReturn(mailGunEmail());
        when(emailService.send(ArgumentMatchers.any(DefaultTemplate.class))).thenReturn(defaultEmail());
    }

    public List<EmailProvider> mockProviders() {
        return List.of(
                new EmailProviderMock(emailService, TOP, SPARK, new SparkTemplate(), emailService.isConnected()),
                new EmailProviderMock(emailService, HIGH, SENDGRID, sendGridTemplate(), emailService.isConnected()),
                new EmailProviderMock(emailService, MEDIUM, MALIGUN, new MailgunTemplate(), emailService.isConnected()),
                new EmailProviderMock(emailService, LOW, DEFAULT, new DefaultTemplate(), emailService.isConnected())
        );
    }
}
