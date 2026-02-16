package com.notify.bridge.exception;

/**
 * Lanzada cuando una solicitud de notificación falla la validación.
 * Esta es una subclase de NotificationException.
 */
/**
 * Excepción que indica que los datos de la notificación no son válidos
 * (validación de dominio fallo).
 */
public class InvalidNotificationException extends NotificationException {
    public InvalidNotificationException(String message) {
        super(message);
    }
}
