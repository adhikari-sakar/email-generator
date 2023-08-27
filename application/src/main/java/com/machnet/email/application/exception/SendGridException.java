package com.machnet.email.application.exception;

public class SendGridException extends RuntimeException {

    public SendGridException(String errorMessage) {
        super(errorMessage);
    }
}
