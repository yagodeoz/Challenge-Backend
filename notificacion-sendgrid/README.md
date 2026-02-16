# Notificación SendGrid Module

Este módulo proporciona la implementación del canal de correo electrónico utilizando **SendGrid**.

## Características
- Implementa `EmailProvider` del core `notifications-lib`.
- Soporte para configuración vía constructor o variable de entorno `SENDGRID_API_KEY`.
- Integración automática vía SPI.

## Uso

### Dependencia Maven
```xml
<dependency>
    <groupId>com.notify.sendgrid</groupId>
    <artifactId>notificacion-sendgrid</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### Configuración
Establece la variable de entorno:
```bash
export SENDGRID_API_KEY="tu-api-key-real"
```

La librería `notifications-lib` detectará y usará este proveedor automáticamente para notificaciones de tipo `EmailNotification`.

## Pruebas
Ejecutar tests unitarios:
```bash
mvn test
```
