package com.notify.sendgrid;

import com.notify.bridge.api.ChannelType;
import com.notify.bridge.api.NotificationResult;
import com.notify.bridge.api.Notifier;
import com.notify.bridge.config.NotifierBuilder;
import com.notify.bridge.domain.model.EmailNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase principal de demostración para el uso de la librería de notificaciones
 * con SendGrid.
 * Muestra el autodescubrimiento de proveedores (SPI) y el envío síncrono.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Punto de entrada de la aplicación demo.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        logger.info("Starting SendGrid Notification Demo...");

        String apiKey = System.getenv("SENDGRID_API_KEY");
        if (apiKey == null) {
            apiKey = "TEST_API_KEY";
            logger.warn("SENDGRID_API_KEY not set, using default: {}", apiKey);
        }

        // Configure Notifier (using SPI for provider discovery)
        Notifier notifier = NotifierBuilder.create()
                .forChannel(ChannelType.EMAIL)
                // .withProvider(...) -> Removed to test SPI auto-discovery
                .build();

        // Create notification
        EmailNotification email = new EmailNotification("user@example.com", "Hello from SendGrid", "This is a test notification.");

        // Send
        try {
            NotificationResult result = notifier.send(email);
            if (result.success()) {
                logger.info("Notification sent successfully! ID: {}", result.messageId());
            } else {
                logger.error("Failed to send notification: {}", result.errorMessage());
            }
        } catch (Exception e) {
            logger.error("Exception sending notification", e);
        }
    }
}
