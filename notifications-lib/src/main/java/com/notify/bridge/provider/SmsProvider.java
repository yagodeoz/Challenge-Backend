package com.notify.bridge.provider;

import com.notify.bridge.domain.model.SmsNotification;

/**
 * Interfaz marcadora o proveedor especializado para SMS.
 * Extiende {@link Provider} para el tipo {@link SmsNotification}.
 */
public interface SmsProvider extends Provider<SmsNotification> {
}
