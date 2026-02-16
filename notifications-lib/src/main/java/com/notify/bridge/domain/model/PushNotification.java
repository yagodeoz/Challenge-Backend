package com.notify.bridge.domain.model;

import com.notify.bridge.domain.validator.PushValidator;

/**
 * Representa una notificación PushNotification.
 *
 * @param deviceToken Token del dispositivo destinatario.
 * @param title       Título de la notificación.
 * @param body        Cuerpo de la notificación.
 */
public record PushNotification(String deviceToken, String title, String body) implements Notification {
    /**
     * Constructor compacto que valida los datos de la notificación
     * PushNotification.
     *
     * @param deviceToken Token del dispositivo.
     * @param title       Título.
     * @param body        Cuerpo.
     */
    public PushNotification {
        PushValidator.validate(deviceToken, title, body);
    }
}
