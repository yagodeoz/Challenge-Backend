package com.notify.bridge.channel;

import com.notify.bridge.domain.model.PushNotification;

import com.notify.bridge.provider.Provider;

/**
 * Implementación del canal de notificaciones PushNotification.
 */
public class PushChannel extends AbstractNotificationChannel<PushNotification> {

    /**
     *
     * @param provider El proveedor PushNotification a utilizar.
     */
    public PushChannel(Provider<PushNotification> provider) {
        super(PushNotification.class, provider);
    }
}
