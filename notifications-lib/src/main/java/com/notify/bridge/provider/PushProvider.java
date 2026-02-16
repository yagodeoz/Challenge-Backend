package com.notify.bridge.provider;

import com.notify.bridge.domain.model.PushNotification;

/**
 * Interfaz marcadora o proveedor especializado para notificaciones PushNotification.
 * Extiende {@link Provider} para el tipo {@link PushNotification}.
 */
public interface PushProvider extends Provider<PushNotification> {
}
