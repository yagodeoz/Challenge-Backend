package com.notify.bridge.domain.model;

/**
 * Interfaz que define un tipo de notificación.
 * Implementaciones deben representar los datos específicos de cada canal
 * (EmailNotification, SMS, etc).
 */
public sealed interface Notification permits EmailNotification, SmsNotification, PushNotification, SlackNotification {
}
