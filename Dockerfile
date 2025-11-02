# Multi-stage build for Spring Boot app
# 1) Build stage
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /workspace

# Copy gradle wrapper and build files first for better caching
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src

# Ensure gradlew is executable (on Linux containers)
RUN chmod +x gradlew && ./gradlew --no-daemon clean bootJar

# 2) Runtime stage
FROM eclipse-temurin:17-jre

# Create non-root user
RUN useradd -ms /bin/bash appuser
WORKDIR /app

# Copy fat jar from builder
COPY --from=builder /workspace/build/libs/*.jar /app/app.jar

# Expose default Spring Boot port
EXPOSE 8080

USER appuser

# Health-friendly JVM defaults; allow overriding via env at runtime
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0"
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar"]
