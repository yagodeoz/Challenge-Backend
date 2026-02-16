package com.notify.bridge.channel;

import com.notify.bridge.domain.model.SmsNotification;
import com.notify.bridge.provider.Provider;

/**
 * Implementación del canal de SMS.
 */
public class SmsChannel extends AbstractNotificationChannel<SmsNotification> {

    /**
     *
     * @param provider El proveedor de SMS a utilizar.
     */
    public SmsChannel(Provider<SmsNotification> provider) {
        super(SmsNotification.class, provider);
    }
}
