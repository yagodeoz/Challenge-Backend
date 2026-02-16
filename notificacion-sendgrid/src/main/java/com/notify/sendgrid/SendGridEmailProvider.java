package com.notify.sendgrid;

import com.notify.bridge.api.NotificationResult;
import com.notify.bridge.domain.model.EmailNotification;
import com.notify.bridge.provider.EmailProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Implementación de {@link EmailProvider} utilizando SendGrid.
 * Esta clase permite enviar correos electrónicos a través del servicio
 * SendGrid.
 */
public class SendGridEmailProvider implements EmailProvider {
    private static final Logger logger = LoggerFactory.getLogger(SendGridEmailProvider.class);
    private final String apiKey;

    /**
     * Constructor por defecto para uso con SPI.
     * Carga la API KEY desde la variable de entorno `SENDGRID_API_KEY`.
     */
    public SendGridEmailProvider() {
        this.apiKey = System.getenv("SENDGRID_API_KEY");
        if (this.apiKey == null) {
            logger.warn("SENDGRID_API_KEY environment variable not set.");
        }
    }

    public SendGridEmailProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public NotificationResult send(EmailNotification email) {
        logger.info("Sending EmailNotification via SendGrid [API_KEY: {}] to {}", apiKey, email.to());

        // Simulation logic
        if (email.body().contains("error")) {
            return NotificationResult.failure("Simulated SendGrid Error");
        }

        return NotificationResult.success(UUID.randomUUID().toString());
    }
}
