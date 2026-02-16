package com.notify.bridge.factory;

import com.notify.bridge.api.NotificationResult;
import com.notify.bridge.api.Notifier;
import com.notify.bridge.domain.model.Notification;
import com.notify.bridge.channel.NotificationChannel;
import com.notify.bridge.config.NotifierConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Fábrica para crear instancias de Notifier.
 */
public class NotifierFactory {

    /**
     * Crea un {@link Notifier} configurado.
     *
     * @param config La configuración del notificador.
     * @return Una instancia de {@link Notifier}.
     */
    public static Notifier createNotifier(NotifierConfig config) {
        Map<Class<? extends Notification>, NotificationChannel> channelMap = new HashMap<>();

        config.getChannels().forEach((type, channelConfig) -> channelMap.put(type, channelConfig.channel()));

        return new SimpleNotifier(channelMap, config.getAsyncExecutor());
    }

    private static class SimpleNotifier implements Notifier {
        private final Map<Class<? extends Notification>, NotificationChannel> channels;
        private final ExecutorService executor;

        public SimpleNotifier(Map<Class<? extends Notification>, NotificationChannel> channels,
                ExecutorService executor) {
            this.channels = channels;
            this.executor = executor;
        }

        @Override
        public NotificationResult send(Notification notification)
                throws com.notify.bridge.exception.NotificationException {
            NotificationChannel channel = channels.get(notification.getClass());
            if (channel == null) {
                return NotificationResult.failure(
                        "No hay canal configurado para el tipo de notificación: " + notification.getClass().getName());
            }
            return channel.send(notification);
        }

        @Override
        public java.util.concurrent.CompletableFuture<NotificationResult> sendAsync(Notification notification) {
            if (executor != null) {
                return java.util.concurrent.CompletableFuture.supplyAsync(() -> {
                    try {
                        return send(notification);
                    } catch (com.notify.bridge.exception.NotificationException e) {
                        throw new java.util.concurrent.CompletionException(e);
                    }
                }, executor);
            }
            return Notifier.super.sendAsync(notification);
        }
    }
}
