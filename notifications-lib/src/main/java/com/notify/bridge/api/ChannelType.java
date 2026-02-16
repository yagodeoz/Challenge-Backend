package com.notify.bridge.api;

/**
 * Canales de notificación soportados.
 */
/**
 * Enumera los tipos de canales de notificación soportados.
 */
public enum ChannelType {
    /** Canal de correo electrónico. */
    EMAIL,
    /** Canal de mensajes de texto (SMS). */
    SMS,
    /** Canal de notificaciones PushNotification. */
    PUSH,
    /** Canal de SlackNotification. */
    SLACK
}
