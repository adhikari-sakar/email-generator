package com.machnet.email.application.service;

import com.machnet.domain.contracts.EmailProvider;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.usecase.EmailGenerateUseCase;
import com.machnet.email.application.mapper.EmailResponseMapper;
import com.machnet.email.application.model.EmailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class EmailGeneratorService {

    private final EmailResponseMapper mapper;
    private final EmailGenerateUseCase useCase;
    private final Map<EmailProviderType, EmailProvider> emailProviders;

    //delegating to domain use case
    //email generation is decoupled from infrastructure code
    public EmailResponse generateEmail(EmailProviderType providerType) {
        return mapper.toResponse(useCase.generateEmail(getProvider(providerType), emailProviders.values()));
    }

    //resolve provider based on provider type
    private EmailProvider getProvider(EmailProviderType providerType) {
        return emailProviders.get(providerType);
    }
}
