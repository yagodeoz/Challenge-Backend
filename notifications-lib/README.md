# Notifications Library (Java)

Librería agnóstica para envío de notificaciones (Email, SMS, Push, Slack) en Java 21+. Diseñada para ser extensible, ligera y fácil de configurar.

## Características
- **Agnóstica**: Sin dependencias de frameworks (Spring, Quarkus, etc.).
- **Extensible**: API basada en interfaces (`Provider`, `Channel`) y SPI.
- **Tipado Seguro**: Modelos inmutables (`EmailNotification`, `SmsNotification`, etc.) con validación integrada.
- **Async**: Soporte nativo para envío asíncrono con `CompletableFuture`.

## Compilación
Para generar los artefactos (JARs) en la carpeta `target/`:
```bash
mvn clean package
```

## Instalación
```bash
mvn clean install
```
Añadir dependencia:
```xml
<dependency>
    <groupId>com.notify.bridge</groupId>
    <artifactId>notifications-lib</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## Uso Rápido

### Enviar Email (Síncrono)
```java
Notifier notifier = NotifierBuilder.create()
    .forType(EmailNotification.class)
    .withProvider(new SendGridProvider(API_KEY)) // O autodescubrimiento vía SPI
    .build();

notifier.send(new EmailNotification("to@example.com", "Subject", "Body"));
```

### Enviar SMS (Asíncrono)
```java
Notifier notifier = NotifierBuilder.create()
    .forType(SmsNotification.class)
    .withProvider(new TwilioProvider(SID, TOKEN))
    .withAsyncExecutor(Executors.newFixedThreadPool(2))
    .build();

notifier.sendAsync(new SmsNotification("+123456789", "Hola Mundo"))
    .thenAccept(result -> System.out.println("Enviado: " + result.messageId()));
```

## Extensión (SPI)
Para añadir un nuevo proveedor (ej. Mailgun), implementa `EmailProvider` y regístralo en `META-INF/services/com.notify.bridge.provider.EmailProvider`. La librería lo cargará automáticamente.

---
- *Requisitos: Java 21+ | Maven 3.6+*
- *Autor:Byron Segovia*
