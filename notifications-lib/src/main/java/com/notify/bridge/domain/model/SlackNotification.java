package com.notify.bridge.domain.model;

/**
 * Modelo de dominio para una notificación de SlackNotification.
 *
 * @param webhookUrl La URL del Webhook de SlackNotification
 * @param channel    El canal específico (ej. #general)
 * @param message    El texto del mensaje.
 */
public record SlackNotification(String webhookUrl, String channel, String message) implements Notification {

    /**
     * Constructor compacto para validación.
     */
    public SlackNotification {
        if (message == null || message.trim().isEmpty()) {
            throw new com.notify.bridge.exception.InvalidNotificationException("El mensaje es obligatorio.");
        }
    }
}
