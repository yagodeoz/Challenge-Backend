package com.notify.bridge.domain.validator;

/**
 * Validador para objetos de dominio {@link com.notify.bridge.domain.model.PushNotification}.
 */
public final class PushValidator {

    private PushValidator() {
    }

    /**
     * Valida los campos de una notificación PushNotification.
     *
     * @param deviceToken Token del dispositivo.
     * @param title       Título.
     * @param body        Cuerpo.
     * @throws IllegalArgumentException Si algún campo es nulo o vacío.
     */
    public static void validate(String deviceToken, String title, String body) {
        validateNotNullOrEmpty(deviceToken, "Token de dispositivo");
        validateNotNullOrEmpty(title, "Título");
        validateNotNullOrEmpty(body, "Cuerpo");
    }

    private static void validateNotNullOrEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " no puede ser nulo o vacío");
        }
    }
}
