package com.machnet.domain.contracts;

import com.machnet.domain.email.EmailModel;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.email.Priority;
import com.machnet.domain.exception.CommandNotFoundException;

import java.util.Collection;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.function.Predicate.not;

public interface EmailServiceProvider extends Command<EmailModel> {

    EmailProviderType getType();

    EmailModel execute();

    Priority getPriority();

    EmailServiceProvider updatePriority(Priority priority);

    EmailServiceProvider skip();

    boolean isSkipped();

    EmailService getService();

    default Boolean isUp() {
        // static ConnectionProvider.isConnected() will be helpful to check connection from another context as well
        return ConnectionProvider.isConnected(getService());
    }

    default EmailServiceProvider skipAndFallback(Collection<EmailServiceProvider> providers) {
        return this.skip().andThen(provider -> fallback(providers));
    }

    EmailServiceProvider andThen(Function<EmailServiceProvider, EmailServiceProvider> providerFunction);

    private EmailServiceProvider fallback(Collection<EmailServiceProvider> providers) {
        return providers.stream()
                .filter(not(EmailServiceProvider::isSkipped))
                .filter(EmailServiceProvider::isUp)
                .max(comparing(provider -> provider.getPriority().getValue()))
                .map(newProvider -> newProvider.updatePriority(this.getPriority().resolve(newProvider.getPriority())))
                .orElseThrow(() -> new CommandNotFoundException("Email Provider not found!!"));
    }
}
