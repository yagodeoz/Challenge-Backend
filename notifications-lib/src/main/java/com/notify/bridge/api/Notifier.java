package com.notify.bridge.api;

import com.notify.bridge.domain.model.Notification;
import com.notify.bridge.exception.NotificationException;


/**
 * Punto de entrada principal para enviar notificaciones.
 * Esta interfaz define las operaciones para el envío síncrono y asíncrono.
 */
public interface Notifier {

    /**
     * Envía una notificación de manera síncrona.
     *
     * @param notification La notificación a enviar.
     * @return El resultado de la operación.
     * @throws NotificationException Si ocurre un error irrecuperable al enviar la
     *                               notificación.
     */
    NotificationResult send(Notification notification) throws NotificationException;

    /**
     * Envía una notificación de manera asíncrona.
     *
     * @param notification La notificación a enviar.
     * @return Un CompletableFuture con el resultado de la operación.
     */
    default java.util.concurrent.CompletableFuture<NotificationResult> sendAsync(Notification notification) {
        return java.util.concurrent.CompletableFuture.supplyAsync(() -> {
            try {
                return send(notification);
            } catch (NotificationException e) {
                throw new java.util.concurrent.CompletionException(e);
            }
        });
    }
}
