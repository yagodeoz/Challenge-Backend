package com.notify.bridge.api;

/**
 * Representa el resultado de un intento de envío de notificación.
 *
 * @param success      Indica si el envío fue exitoso.
 * @param messageId    Identificador único del mensaje (si fue exitoso).
 * @param errorMessage Mensaje de error (si falló).
 */
public record NotificationResult(boolean success, String messageId, String errorMessage) {

    /**
     * Crea un resultado exitoso.
     *
     * @param messageId ID del mensaje generado por el proveedor.
     * @return Un objeto NotificationResult indicando éxito.
     */
    public static NotificationResult success(String messageId) {
        return new NotificationResult(true, messageId, null);
    }

    /**
     * Crea un resultado fallido.
     *
     * @param errorMessage Descripción del error ocurrido.
     * @return Un objeto NotificationResult indicando fallo.
     */
    public static NotificationResult failure(String errorMessage) {
        return new NotificationResult(false, null, errorMessage);
    }
}
