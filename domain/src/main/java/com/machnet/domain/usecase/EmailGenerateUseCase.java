package com.machnet.domain.usecase;

import com.machnet.domain.contracts.EmailProvider;
import com.machnet.domain.email.EmailModel;

import java.util.Collection;

public class EmailGenerateUseCase {

    //command pattern, caller is unaware about the provider implementation
    public EmailModel generateEmail(EmailProvider provider, Collection<EmailProvider> allProviders) {
        if (provider.isUp()) {
            return provider.execute();
        }
        //skip and fallback next available provider
        return provider.skipAndFallback(allProviders).execute();
    }
}

