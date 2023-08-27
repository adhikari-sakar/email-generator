package com.machnet.domain.contracts;

import com.machnet.domain.exception.CommandNotFoundException;
import com.machnet.domain.template.DefaultTemplate;
import com.machnet.domain.usecase.BaseTest;
import com.machnet.domain.usecase.EmailProviderMock;
import org.junit.jupiter.api.Test;

import static com.machnet.domain.email.EmailProviderType.DEFAULT;
import static com.machnet.domain.email.EmailProviderType.SPARK;
import static com.machnet.domain.email.Priority.MEDIUM;
import static com.machnet.domain.email.Priority.TOP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class EmailProviderTest extends BaseTest {

    private final EmailProvider mockProvider = new EmailProviderMock(emailService, MEDIUM, DEFAULT,
            new DefaultTemplate(), true);

    @Test
    void fallback_givenAvailableProviders_thenSortedAndReturnByPriority() {
        when(emailService.isConnected()).thenReturn(true);
        var candidate = mockProvider.fallback(mockProviders());
        assertThat(candidate.getPriority()).isEqualTo(TOP);
        assertThat(candidate.getType()).isEqualTo(SPARK);
    }

    @Test
    void fallback_givenAllSkippedProviders_thenCommandNotFoundException() {
        when(emailService.isConnected()).thenReturn(false);
        assertThrows(CommandNotFoundException.class, () -> mockProvider.fallback(mockProviders()));
    }

    @Test
    void fallback_givenAllProvidersAreDown_thenCommandNotFoundException() {
        when(emailService.isConnected()).thenReturn(false);
        assertThrows(CommandNotFoundException.class, () -> mockProvider.fallback(mockProviders()));
    }
}