package com.notify.bridge.domain.validator;

import java.util.regex.Pattern;

/**
 * Validador para objetos de dominio {@link SmsValidator}.
 */
public final class SmsValidator {

    private static final Pattern E164_PATTERN = Pattern.compile("^\\+[1-9]\\d{1,14}$");

    private SmsValidator() {
    }

    /**
     * Valida los campos de un SMS.
     *
     * @param phoneNumber Número de teléfono.
     * @param message     Mensaje.
     * @throws com.notify.bridge.exception.InvalidNotificationException Si algún campo es inválido.
     */
    public static void validate(String phoneNumber, String message) {
        validatePhoneNumber(phoneNumber);
        validateNotNullOrEmpty(message, "Mensaje");
    }

    private static void validatePhoneNumber(String phoneNumber) {
        validateNotNullOrEmpty(phoneNumber, "Número de teléfono");
        if (!E164_PATTERN.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException(
                    "Formato de teléfono inválido (debe ser E.164, ej: +123456789): " + phoneNumber);
        }
    }

    private static void validateNotNullOrEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " no puede ser nulo o vacío");
        }
    }
}
