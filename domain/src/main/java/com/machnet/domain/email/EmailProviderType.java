package com.machnet.domain.email;

import com.machnet.domain.exception.CommandNotFoundException;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum EmailProviderType {
    MALIGUN("mailgun"),
    SPARK("spark"),
    SENDGRID("sendgrid"),
    DEFAULT("default");

    private final String providerType;

    EmailProviderType(String type) {
        this.providerType = type;
    }

    public static EmailProviderType getType(String providerType) {
        return Stream.of(values())
            .filter(type -> type.getProviderType().equalsIgnoreCase(providerType))
            .findFirst()
            .orElseThrow(() -> new CommandNotFoundException("Email Provider not found!!"));
    }
}
