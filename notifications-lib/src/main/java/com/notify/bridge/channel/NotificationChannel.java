package com.notify.bridge.channel;

import com.notify.bridge.domain.model.Notification;
import com.notify.bridge.api.NotificationResult;
import com.notify.bridge.exception.NotificationException;

/**
 * Interfaz estándar para todos los canales de notificación.
 */
public interface NotificationChannel {

    /**
     * Envía la notificación a través de este canal.
     *
     * @param notification La notificación a enviar.
     * @return El resultado de la operación.
     * @throws NotificationException Si ocurre un error.
     */
    NotificationResult send(Notification notification) throws NotificationException;
}
