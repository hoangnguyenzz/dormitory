FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk wget unzip

WORKDIR /app

COPY . .

RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/build/libs/quanlykytucxa-0.0.1-SNAPSHOT.jar app.jar

# Giới hạn RAM để tránh crash Railway
ENV JAVA_TOOL_OPTIONS="-Xmx256m -Xms128m"

ENTRYPOINT ["java", "-jar", "app.jar"]
