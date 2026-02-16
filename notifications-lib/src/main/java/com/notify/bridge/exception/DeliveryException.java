package com.notify.bridge.exception;

/**
 * Excepción que indica un fallo en la entrega de la notificación por parte del
 * proveedor externo.
 */
public class DeliveryException extends NotificationException {
    public DeliveryException(String message) {
        super(message);
    }

    public DeliveryException(String message, Throwable cause) {
        super(message, cause);
    }
}
