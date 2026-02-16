package com.notify.sendgrid;

import com.notify.bridge.api.NotificationResult;
import com.notify.bridge.domain.model.EmailNotification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SendGridEmailProviderTest {

    private SendGridEmailProvider provider;

    @BeforeEach
    void setUp() {
        provider = new SendGridEmailProvider("test-api-key");
    }

    @Test
    void testSendSuccess() {
        EmailNotification email = new EmailNotification("test@example.com", "Subject", "Body");
        NotificationResult result = provider.send(email);

        assertTrue(result.success());
        assertNotNull(result.messageId());
    }

    @Test
    void testSendFailure() {
        // The provider simulates failure if body contains "error"
        EmailNotification email = new EmailNotification("test@example.com", "Subject", "Body with error");
        NotificationResult result = provider.send(email);

        assertFalse(result.success());
        assertNotNull(result.errorMessage());
        assertTrue(result.errorMessage().contains("Simulated SendGrid Error"));
    }
}
