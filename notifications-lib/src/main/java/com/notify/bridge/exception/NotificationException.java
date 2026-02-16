package com.notify.bridge.exception;

/**
 * Excepción base para todos los errores relacionados con el envío de
 * notificaciones.
 * Es una RuntimeException para no obligar al cliente a capturarla si no lo
 * desea.
 */
public class NotificationException extends RuntimeException {
    public NotificationException(String message) {
        super(message);
    }

    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
