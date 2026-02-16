package com.notify.bridge.domain.validator;

import com.notify.bridge.domain.model.SlackNotification;
import com.notify.bridge.exception.InvalidNotificationException;

/**
 * Validador para notificaciones de SlackNotification.
 */
public class SlackValidator {

    /**
     * Valida una notificación de SlackNotification.
     *
     * @param slack La notificación a validar.
     * @throws InvalidNotificationException Si la validación falla.
     */
    public static void validate(SlackNotification slack) {
        if (slack.webhookUrl() == null || slack.webhookUrl().trim().isEmpty()) {
            throw new InvalidNotificationException("El Webhook URL es obligatorio.");
        }
        if (slack.message() == null || slack.message().trim().isEmpty()) {
            throw new InvalidNotificationException("El mensaje es obligatorio.");
        }
    }
}
