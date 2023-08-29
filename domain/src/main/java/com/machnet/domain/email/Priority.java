package com.machnet.domain.email;

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
    public Priority resolve(Priority priority) {
        return this.getValue().compareTo(priority.getValue()) > 0 ? this : priority;
    }
}
