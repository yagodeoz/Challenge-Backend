package com.notify.bridge.domain.model;

import com.notify.bridge.domain.validator.EmailValidator;

/**
 * Representa una notificación por correo electrónico.
 *
 * @param to      Destinatario del correo.
 * @param subject Asunto del correo.
 * @param body    Cuerpo del mensaje.
 */
public record EmailNotification(String to, String subject, String body) implements Notification {
    /**
     * Constructor compacto que valida los datos del email.
     *
     * @param to      Destinatario.
     * @param subject Asunto.
     * @param body    Cuerpo.
     */
    public EmailNotification {
        EmailValidator.validate(to, subject, body);
    }
}
