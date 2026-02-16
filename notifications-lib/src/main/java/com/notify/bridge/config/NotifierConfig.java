package com.notify.bridge.config;

import com.notify.bridge.domain.model.Notification;
import com.notify.bridge.channel.NotificationChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Configuración para el Notificador.
 * Almacena los canales configurados y el ejecutor para operaciones asíncronas.
 */
public class NotifierConfig {

    private final Map<Class<? extends Notification>, ChannelConfig> channels = new HashMap<>();
    private ExecutorService asyncExecutor;

    public NotifierConfig() {
    }

    /**
     * Agrega la configuración de un canal.
     *
     * @param notificationType Tipo de notificación (clase).
     * @param channel Canal de notificación
     */
    public void addChannel(Class<? extends Notification> notificationType, NotificationChannel channel) {
        channels.put(notificationType, new ChannelConfig(channel));
    }

    public Map<Class<? extends Notification>, ChannelConfig> getChannels() {
        return channels;
    }

    public void setAsyncExecutor(ExecutorService asyncExecutor) {
        this.asyncExecutor = asyncExecutor;
    }

    public ExecutorService getAsyncExecutor() {
        return asyncExecutor;
    }

    public record ChannelConfig(NotificationChannel channel) {
    }
}
