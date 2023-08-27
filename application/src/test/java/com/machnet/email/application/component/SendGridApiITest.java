package com.machnet.email.application.component;

import com.machnet.domain.contracts.EmailTemplate;
import com.machnet.domain.email.EmailBody;
import com.machnet.domain.email.EmailReceiver;
import com.machnet.domain.email.EmailSender;
import com.machnet.domain.email.EmailSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SendGridApiITest {

    @Autowired
    private SendGridApi sendGridApi;

    //uncomment to perform integration test
//    @Test()
    void sendEmail() throws IOException {
        var emailResponse = sendGridApi.sendEmail(new MyTemplate());
        assertThat(emailResponse).isNotNull();
        assertThat(emailResponse.getStatusCode()).isEqualTo(202);
    }

    //    @Test
    void isConnected() {
        assertThat(sendGridApi.isConnected()).isTrue();
    }

    static class MyTemplate implements EmailTemplate {

        @Override
        public EmailSender getFrom() {
            return new EmailSender("bfren.sakar@gmail.com");
        }

        @Override
        public EmailSubject getSubject() {
            return new EmailSubject("SendGrid Integration Test");
        }

        @Override
        public EmailBody getBody() {
            return new EmailBody("This is a test email");
        }

        @Override
        public EmailReceiver getTo() {
            return new EmailReceiver("sakar.adhikari@proshore.eu");
        }
    }
}