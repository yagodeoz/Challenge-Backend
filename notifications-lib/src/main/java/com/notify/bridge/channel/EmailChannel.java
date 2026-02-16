package com.notify.bridge.channel;

import com.notify.bridge.domain.model.EmailNotification;
import com.notify.bridge.provider.Provider;

/**
 * Implementación del canal de correo electrónico.
 */
public class EmailChannel extends AbstractNotificationChannel<EmailNotification> {

    /**
     * Crea un nuevo canal de email.
     *
     * @param provider El proveedor de email a utilizar.
     */
    public EmailChannel(Provider<EmailNotification> provider) {
        super(EmailNotification.class, provider);
    }
}
