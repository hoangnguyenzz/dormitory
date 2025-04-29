# FROM ubuntu:latest AS build

# RUN apt-get update
# RUN apt-get install openjdk-17-jdk -y
# COPY . .

# RUN ./gradlew bootJar --no-daemon

# FROM openjdk:17-jdk-slim

# EXPOSE 8080

# COPY --from=build /build/libs/quanlykytucxa-0.0.1-SNAPSHOT.jar app.jar

# ENTRYPOINT ["java", "-jar", "app.jar"]



FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk wget unzip

WORKDIR /app

COPY . .

RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /app

# Lắng nghe đúng cổng, Railway sẽ map từ biến môi trường PORT
EXPOSE 8080

COPY --from=build /app/build/libs/quanlykytucxa-0.0.1-SNAPSHOT.jar app.jar

# Truyền biến môi trường PORT cho Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
