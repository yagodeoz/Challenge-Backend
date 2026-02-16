package com.notify.bridge.channel;

import com.notify.bridge.api.NotificationResult;
import com.notify.bridge.domain.model.Notification;
import com.notify.bridge.exception.InvalidNotificationException;
import com.notify.bridge.exception.NotificationException;

/**
 * Clase base abstracta para canales de notificación que manejan un tipo
 * específico de {@link Notification}.
 * Implementa el patrón Template Method para realizar la validación de tipos y
 * el casting de manera centralizada.
 *
 * @param <T> El tipo concreto de {@link Notification} que este canal maneja.
 */
public abstract class AbstractNotificationChannel<T extends Notification> implements NotificationChannel {

    private final Class<T> notificationType;
    private final com.notify.bridge.provider.Provider<T> provider;

    /**
     * Constructor.
     *
     * @param notificationType La clase del tipo de notificación esperado.
     * @param provider         El proveedor que realizará el envío real.
     */
    protected AbstractNotificationChannel(Class<T> notificationType, com.notify.bridge.provider.Provider<T> provider) {
        this.notificationType = notificationType;
        this.provider = provider;
    }

    /**
     * Envía una notificación, previa validación de que su tipo coincide con el esperado.
     * @param notification la notificación a enviar, no debe ser {@code null}.
     * @return el resultado del envío de la notificación.
     * @throws InvalidNotificationException si el tipo de la notificación no coincide con el
     *         esperado ({@code notificationType}).
     * @throws NotificationException        si ocurre un error durante el proceso de envío.
     */
    @Override
    public final NotificationResult send(Notification notification) throws NotificationException {
        if (!notificationType.isInstance(notification)) {
            throw new InvalidNotificationException(
                    "El tipo de notificación inválido. Se esperaba: " + notificationType.getSimpleName() +
                            ", pero se recibió: " + notification.getClass().getSimpleName());
        }
        return sendTyped(notificationType.cast(notification));
    }

    /**
     * Envía la notificación fuertemente tipada usando el proveedor configurado.
     *
     * @param notification La notificación ya validada y casteada.
     * @return El resultado del envío.
     * @throws NotificationException Si ocurre un error durante el envío.
     */
    protected NotificationResult sendTyped(T notification) throws NotificationException {
        try {
            return provider.send(notification);
        } catch (Exception e) {
            throw new com.notify.bridge.exception.DeliveryException(
                    "Error sending " + notificationType.getSimpleName() + ": " + e.getMessage());
        }
    }
}
