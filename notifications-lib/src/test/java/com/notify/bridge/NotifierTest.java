package com.notify.bridge;

import com.notify.bridge.api.ChannelType;
import com.notify.bridge.api.NotificationResult;
import com.notify.bridge.api.Notifier;
import com.notify.bridge.config.NotifierBuilder;
import com.notify.bridge.domain.model.EmailNotification;
import com.notify.bridge.provider.EmailProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotifierTest {

    @Mock
    private EmailProvider emailProvider;

    @Test
    void testSendEmailSync() throws Exception {
        // Arrange
        when(emailProvider.send(any(EmailNotification.class)))
                .thenReturn(NotificationResult.success("msg-id-123"));

        Notifier notifier = NotifierBuilder.create()
                .forChannel(ChannelType.EMAIL)
                .withProvider(emailProvider)
                .build();

        EmailNotification email = new EmailNotification("test@example.com", "Subject", "Body");

        // Act
        NotificationResult result = notifier.send(email);

        // Assert
        assertTrue(result.success());
        assertEquals("msg-id-123", result.messageId());
        verify(emailProvider).send(email);
    }

    @Test
    void testSendEmailAsync() throws Exception {
        // Arrange
        when(emailProvider.send(any(EmailNotification.class)))
                .thenReturn(NotificationResult.success("msg-id-async"));

        Notifier notifier = NotifierBuilder.create()
                .forChannel(ChannelType.EMAIL)
                .withProvider(emailProvider)
                .withAsyncExecutor(Executors.newSingleThreadExecutor())
                .build();

        EmailNotification email = new EmailNotification("test@example.com", "Subject", "Body");

        // Act
        CompletableFuture<NotificationResult> future = notifier.sendAsync(email);
        NotificationResult result = future.get(); // block to verify

        // Assert
        assertTrue(result.success());
        assertEquals("msg-id-async", result.messageId());
        verify(emailProvider).send(email);
    }
}
