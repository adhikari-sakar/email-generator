package com.machnet.email.application.model;

import lombok.Data;

@Data
public class EmailResponse {

    private String subject;
    private String sender;
    private String body;
    private String receiver;
    private String status;
}
