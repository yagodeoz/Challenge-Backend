# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /build

# 1. Copiar POMs primero para cachear dependencias
COPY notifications-lib/pom.xml notifications-lib/pom.xml
COPY notificacion-sendgrid/pom.xml notificacion-sendgrid/pom.xml

# 2. Descargar dependencias (sin compilar código fuente aún)
# Primero la librería core
RUN mvn -f notifications-lib/pom.xml dependency:go-offline -B

# 3. Compilar e Instalar librería core
COPY notifications-lib/src notifications-lib/src
RUN mvn -f notifications-lib/pom.xml clean install -DskipTests

# 4. Compilar aplicación SendGrid (ahora que core está instalado)
COPY notificacion-sendgrid/src notificacion-sendgrid/src
RUN mvn -f notificacion-sendgrid/pom.xml clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Seguridad: Crear usuario no-root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=build /build/notificacion-sendgrid/target/notificacion-sendgrid-1.0.0-SNAPSHOT.jar app.jar
ENV SENDGRID_API_KEY=""

CMD ["java", "-jar", "app.jar"]
