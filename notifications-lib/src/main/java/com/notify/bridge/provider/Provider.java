package com.notify.bridge.provider;

import com.notify.bridge.api.NotificationResult;
import com.notify.bridge.domain.model.Notification;

/**
 * Interfaz genérica para proveedores de notificaciones.
 *
 * @param <T> El tipo de notificación que este proveedor maneja.
 */
public interface Provider<T extends Notification> {
    /**
     * Envía una notificación.
     *
     * @param notification La notificación a enviar.
     * @return El resultado del envío.
     * @throws Exception Si ocurre un error durante el envío.
     */
    NotificationResult send(T notification) throws Exception;
}
