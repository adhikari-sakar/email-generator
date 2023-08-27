package com.machnet.email.application.mapper;

import com.machnet.domain.email.EmailModel;
import com.machnet.email.application.model.EmailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmailResponseMapper {

    @Mapping(source = "subject.subject", target = "subject")
    @Mapping(source = "sender.emailAddress", target = "sender")
    @Mapping(source = "body.body", target = "body")
    @Mapping(source = "receiver.emailAddress", target = "receiver")
    EmailResponse toResponse(EmailModel email);
}
