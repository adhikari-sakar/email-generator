package com.machnet.domain.exception;

public class CommandNotFoundException extends RuntimeException {

    public CommandNotFoundException(String message) {
        super(message);
    }
}
