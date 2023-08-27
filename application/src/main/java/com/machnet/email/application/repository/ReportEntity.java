package com.machnet.email.application.repository;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

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
