package com.notify.bridge.channel;

import com.notify.bridge.domain.model.SlackNotification;
import com.notify.bridge.provider.Provider;

/**
 * Canal de notificación para SlackNotification.
 */
public class SlackChannel extends AbstractNotificationChannel<SlackNotification> {

    /**
     *
     * @param provider El proveedor de SlackNotification.
     */
    public SlackChannel(Provider<SlackNotification> provider) {
        super(SlackNotification.class, provider);
    }
}
