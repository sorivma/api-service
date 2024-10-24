FROM gradle:jdk21-alpine AS build

WORKDIR /api-service

COPY build.gradle.kts settings.gradle.kts /api-service/
COPY src /api-service/src

RUN gradle build --no-daemon

FROM openjdk:21-jdk-slim

WORKDIR /api-service

COPY --from=build /api-service/build/libs/*.jar api-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api-service.jar"]
