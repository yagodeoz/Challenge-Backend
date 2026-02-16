package com.notify.bridge.domain.model;

import com.notify.bridge.domain.validator.SmsValidator;

/**
 * Representa una notificación por SMS.
 *
 * @param phoneNumber Número de teléfono destinatario.
 * @param message     Contenido del mensaje de texto.
 */
public record SmsNotification(String phoneNumber, String message) implements Notification {
    /**
     * Constructor compacto que valida los datos del SMS.
     *
     * @param phoneNumber Número de teléfono.
     * @param message     Mensaje de texto.
     */
    public SmsNotification {
        SmsValidator.validate(phoneNumber, message);
    }
}
