package com.machnet.domain.contracts;

import static java.util.Comparator.comparing;
import static java.util.function.Predicate.not;

import com.machnet.domain.email.EmailModel;
import com.machnet.domain.email.EmailProviderType;
import com.machnet.domain.email.Priority;
import com.machnet.domain.exception.CommandNotFoundException;
import java.util.Collection;

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

    default EmailProvider fallback(Collection<EmailProvider> providers) {
        return providers.stream()
            .filter(not(EmailProvider::isSkipped))
            .filter(EmailProvider::isUp)
            .max(comparing(provider -> provider.getPriority().getValue())) //find a provider with the highest priority
            .map(newProvider -> newProvider.updatePriority(this.getPriority().resolve(newProvider)))
            .orElseThrow(() -> new CommandNotFoundException("Email Provider not found!!"));
    }
}
