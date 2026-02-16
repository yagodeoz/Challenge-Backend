package com.notify.bridge.domain.validator;

import java.util.regex.Pattern;

/**
 * Validador para objetos de dominio {@link com.notify.bridge.domain.model.EmailNotification}.
 */
public final class EmailValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private EmailValidator() {
    }

    /**
     * Valida los campos de un email.
     *
     * @param to      Destinatario.
     * @param subject Asunto.
     * @param body    Cuerpo.
     * @throws com.notify.bridge.exception.InvalidNotificationException Si algún campo es inválido.
     */
    public static void validate(String to, String subject, String body) {
        validateEmail(to);
        validateNotNullOrEmpty(subject, "Asunto");
        validateNotNullOrEmpty(body, "Cuerpo");
    }

    private static void validateEmail(String email) {
        validateNotNullOrEmpty(email, "EmailNotification");
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Formato de email inválido: " + email);
        }
    }

    private static void validateNotNullOrEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " no puede ser nulo o vacío");
        }
    }
}
