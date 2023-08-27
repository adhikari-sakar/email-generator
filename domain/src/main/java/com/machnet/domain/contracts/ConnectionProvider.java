package com.machnet.domain.contracts;

public interface ConnectionProvider {

    //result depends on provider specific email service
    static boolean isConnected(EmailService emailService) {
        return emailService.isConnected();
    }
}
