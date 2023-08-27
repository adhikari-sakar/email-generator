package com.machnet.email.application.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ReportEntity extends BaseEntity {

    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String ip_address;
}
