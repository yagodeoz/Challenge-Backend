package com.notify.bridge.provider;

import com.notify.bridge.domain.model.EmailNotification;

/**
 * Interfaz marcadora o proveedor especializado para EmailNotification.
 * Extiende {@link Provider} para el tipo {@link EmailNotification}.
 */
public interface EmailProvider extends Provider<EmailNotification> {
}
