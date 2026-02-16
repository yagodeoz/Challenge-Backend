# Notification System (Monorepo)

Sistema modular y agnóstico para el envío de notificaciones multicanal (Email, SMS, Push, Slack), diseñado bajo principios de **Arquitectura Hexagonal**, **SOLID (OCP)** y **SPI (Service Provider Interface)**.

## Estructura del Proyecto

El repositorio se organiza en módulos independientes:

| Módulo | Descripción | Tecnología Clave |
| :--- | :--- | :--- |
| `notifications-lib` | **Core Library**. Contiene el API, Dominio, Validaciones y Contratos (Interfaces). No tiene implementaciones concretas. | Java 21, Sealed Classes, Records |
| `notificacion-sendgrid` | Implementación del proveedor de **Email** usando **SendGrid**. | HttpClient, SPI |

## Arquitectura

El sistema está diseñado para ser **extensible** sin modificar el núcleo:
- **Core (Agnóstico)**: Define `Notifier`, `NotificationChannel`, y modelos (`EmailNotification`, `SlackNotification`).
- **Proveedores (Pluggable)**: Las implementaciones (e.g., SendGrid) se cargan dinámicamente mediante `java.util.ServiceLoader` (SPI).
- **Seguridad de Tipos**: Uso de `Pattern Matching for Switch` y validaciones en records inmutables.

## Guía de Construcción (Build)

Dado que es un proyecto multi-módulo sin un padre agregador, se debe construir en orden:

### 1. Compilar e Instalar el Core
Es **obligatorio** instalar primero la librería base para que los módulos la encuentren:
```bash
cd notifications-lib
mvn clean install
cd ..
```

### 2. Compilar Implementaciones
Una vez instalado el core, puedes compilar los proveedores:

**SendGrid:**
```bash
cd notificacion-sendgrid
mvn clean package
```

## Guía de Uso Rápido

Para usar el sistema en tu aplicación, añade la dependencia del core y del proveedor que necesites:

```xml
<!-- Core Lib -->
<dependency>
    <groupId>com.notify.bridge</groupId>
    <artifactId>notifications-lib</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>

<!-- Proveedor (Ej. SendGrid) -->
<dependency>
    <groupId>com.notify.sendgrid</groupId>
    <artifactId>notificacion-sendgrid</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### Ejemplo de Código
El sistema detectará automáticamente la implementación de SendGrid en el classpath:

```java
// 1. Crear el Notificador (Auto-descubrimiento)
Notifier notifier = NotifierBuilder.create()
    .forType(EmailNotification.class)
    .build();

// 2. Enviar Notificación
notifier.send(new EmailNotification("usuario@ejemplo.com", "Hola", "Bienvenido al sistema"));
```

## Requisitos
- **Java 21** o superior.
- **Maven 3.6** o superior.

## Autor
- **Byron Segovia
- **https://www.youtube.com/@yagoDeOz82/videos
