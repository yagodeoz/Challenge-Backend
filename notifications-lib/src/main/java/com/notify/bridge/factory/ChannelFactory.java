package com.notify.bridge.factory;

import com.notify.bridge.channel.EmailChannel;
import com.notify.bridge.channel.NotificationChannel;
import com.notify.bridge.channel.PushChannel;
import com.notify.bridge.channel.SmsChannel;
import com.notify.bridge.provider.EmailProvider;
import com.notify.bridge.provider.Provider;
import com.notify.bridge.provider.PushProvider;
import com.notify.bridge.provider.SmsProvider;

/**
 * Fábrica para crear Canales de Notificación específicos.
 */
public class ChannelFactory {

    /**
     * Crea un canal de notificación basado en el proveedor.
     * Utiliza Pattern Matching for Switch (Java 21) para determinar la
     * implementación del canal.
     *
     * @param provider El proveedor asociado al canal.
     * @return Una instancia de {@link NotificationChannel}.
     * @throws IllegalArgumentException Si el proveedor no es soportado.
     */
    public static NotificationChannel createChannel(Provider<?> provider) {
        return switch (provider) {
            case EmailProvider p -> new EmailChannel(p);
            case SmsProvider p -> new SmsChannel(p);
            case com.notify.bridge.provider.SlackProvider p -> new com.notify.bridge.channel.SlackChannel(p);
            case PushProvider p -> new PushChannel(p);
            default -> throw new IllegalArgumentException(
                    "Tipo de proveedor no soportado o desconocido: " + provider.getClass().getName());
        };
    }
}
