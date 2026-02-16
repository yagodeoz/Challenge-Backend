package com.notify.bridge.config;

import com.notify.bridge.api.ChannelType;
import com.notify.bridge.api.Notifier;
import com.notify.bridge.domain.model.EmailNotification;
import com.notify.bridge.domain.model.Notification;
import com.notify.bridge.domain.model.PushNotification;
import com.notify.bridge.domain.model.SlackNotification;
import com.notify.bridge.domain.model.SmsNotification;
import com.notify.bridge.channel.NotificationChannel;
import com.notify.bridge.factory.ChannelFactory;
import com.notify.bridge.factory.NotifierFactory;
import com.notify.bridge.provider.Provider;

import java.util.concurrent.ExecutorService;

/**
 * Builder para configurar y crear instancias de {@link Notifier}.
 * Permite una configuración fluida de canales y proveedores.
 */
public class NotifierBuilder {

    private final NotifierConfig config = new NotifierConfig();

    private Class<? extends Notification> currentNotificationType;
    private NotificationChannel currentChannelInstance;
    private Provider<?> currentProvider;

    private NotifierBuilder() {
    }

    /**
     * Inicia la creación del builder.
     *
     * @return Una nueva instancia de NotifierBuilder.
     */
    public static NotifierBuilder create() {
        return new NotifierBuilder();
    }



    /**
     * Selecciona el canal a configurar usando el enum estándar (Compatibilidad).
     *
     * @param type El tipo de canal.
     * @return El builder para encadenar llamadas.
     */
    public NotifierBuilder forChannel(ChannelType type) {
        flushCurrentConfig();
        this.currentNotificationType = switch (type) {
            case EMAIL -> EmailNotification.class;
            case SMS -> SmsNotification.class;
            case PUSH -> PushNotification.class;
            case SLACK -> SlackNotification.class;
        };
        return this;
    }

    /**
     * Configura el proveedor para el canal seleccionado.
     * Si es un tipo estándar, se usará ChannelFactory para crear el canal.
     *
     * @param provider El proveedor a utilizar.
     * @return El builder.
     */
    public NotifierBuilder withProvider(Provider<?> provider) {
        this.currentProvider = provider;
        return this;
    }

    /**
     * Configura un ejecutor para notificaciones asíncronas.
     *
     * @param executor El ExecutorService.
     * @return El builder.
     */
    public NotifierBuilder withAsyncExecutor(ExecutorService executor) {
        config.setAsyncExecutor(executor);
        return this;
    }

    /**
     * Construye la instancia de Notifier.
     *
     * @return Un Notifier configurado.
     */
    public Notifier build() {
        flushCurrentConfig();
        return NotifierFactory.createNotifier(config);
    }

    private void flushCurrentConfig() {
        if (currentNotificationType != null) {
            NotificationChannel channelToAdd = currentChannelInstance;

            // Si no hay canal explícito, intentamos crearlo desde el proveedor o SPI
            if (channelToAdd == null) {
                if (currentProvider == null) {
                    // Intento de autodescubrimiento vía SPI para tipos estándar
                    currentProvider = loadProviderForType(currentNotificationType);
                }

                if (currentProvider != null) {
                    // Intentar usar ChannelFactory para tipos conocidos
                    ChannelType legacyType = resolveLegacyType(currentNotificationType);
                    if (legacyType != null) {
                        channelToAdd = ChannelFactory.createChannel(currentProvider);
                    } else {
                        // Aquí podríamos lanzar excepción o requerir withChannel obligatoriamente para
                        // custom types
                        throw new IllegalStateException(
                                "Para tipos personalizados (" + currentNotificationType.getSimpleName() +
                                        "), debes usar .withChannel() o extender ChannelFactory.");
                    }
                }
            }

            if (channelToAdd == null) {
                // Si llegamos aquí y es un tipo estándar, es que falló el SPI o no se configuró
                // nada
                throw new IllegalStateException(
                        "No se pudo configurar un canal para " + currentNotificationType.getSimpleName() +
                                ". Falta proveedor o canal explícito.");
            }

            config.addChannel(currentNotificationType, channelToAdd);

            // Limpiar estado
            currentNotificationType = null;
            currentChannelInstance = null;
            currentProvider = null;
        }
    }

    private ChannelType resolveLegacyType(Class<? extends Notification> type) {
        if (EmailNotification.class.isAssignableFrom(type))
            return ChannelType.EMAIL;
        if (SmsNotification.class.isAssignableFrom(type))
            return ChannelType.SMS;
        if (PushNotification.class.isAssignableFrom(type))
            return ChannelType.PUSH;
        if (SlackNotification.class.isAssignableFrom(type))
            return ChannelType.SLACK;
        return null;
    }

    private Provider<?> loadProviderForType(Class<? extends Notification> type) {
        ChannelType legacyType = resolveLegacyType(type);
        if (legacyType == null)
            return null;

        return switch (legacyType) {
            case EMAIL -> java.util.ServiceLoader.load(com.notify.bridge.provider.EmailProvider.class)
                    .findFirst().orElse(null);
            case SMS -> java.util.ServiceLoader.load(com.notify.bridge.provider.SmsProvider.class)
                    .findFirst().orElse(null);
            case PUSH -> java.util.ServiceLoader.load(com.notify.bridge.provider.PushProvider.class)
                    .findFirst().orElse(null);
            case SLACK -> java.util.ServiceLoader.load(com.notify.bridge.provider.SlackProvider.class)
                    .findFirst().orElse(null);
        };
    }
}
