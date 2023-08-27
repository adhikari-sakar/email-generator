package com.machnet.domain.email;

import com.machnet.domain.contracts.EmailProvider;
import lombok.Getter;

@Getter
public enum Priority {
    TOP(100),
    HIGH(75),
    MEDIUM(50),
    LOW(25);

    private final Integer value;

    Priority(int priority) {
        this.value = priority;
    }

    // swap priority from failed provider to new provider if higher, or else unchanged.
    public Priority resolve(EmailProvider fallbackProvider) {
        return this.getValue().compareTo(fallbackProvider.getPriority().getValue()) > 0 ? this
            : fallbackProvider.getPriority();
    }
}
