package com.machnet.domain.contracts;

import com.machnet.domain.email.EmailModel;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.email.Priority;
import com.machnet.domain.exception.CommandNotFoundException;
import com.machnet.domain.provider.AbstractEmailProvider;

import java.util.Collection;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.function.Predicate.not;

public interface EmailProvider extends Command<EmailModel> {

    EmailProviderType getType();

    EmailModel execute();

    Priority getPriority();

    EmailProvider updatePriority(Priority priority);

    EmailProvider skip();

    boolean isSkipped();

    EmailService getService();

    default Boolean isUp() {
        // static ConnectionProvider.isConnected() will be helpful to check connection from another context as well
        return ConnectionProvider.isConnected(getService());
    }

    default EmailProvider skipAndFallback(Collection<EmailProvider> providers) {
        return this.skip().andThen(provider -> fallback(providers));
    }

    EmailProvider andThen(Function<EmailProvider, EmailProvider> providerFunction);

    private EmailProvider fallback(Collection<EmailProvider> providers) {
        return providers.stream()
                .filter(not(EmailProvider::isSkipped))
                .filter(EmailProvider::isUp)
                .max(comparing(provider -> provider.getPriority().getValue()))
                .map(newProvider -> newProvider.updatePriority(this.getPriority().resolve(newProvider.getPriority())))
                .orElseThrow(() -> new CommandNotFoundException("Email Provider not found!!"));
    }
}
