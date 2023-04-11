FROM maven:3.8.6-openjdk-8 AS BUILD_IMAGE
COPY . /home/source/java
WORKDIR /home/source/java

RUN mvn clean package

FROM openjdk:8-jre-alpine

ENV APPLICATION_USER spring-boot
RUN adduser -D -g '' $APPLICATION_USER

RUN mkdir /app
RUN chown -R $APPLICATION_USER /app

USER $APPLICATION_USER

COPY --from=BUILD_IMAGE /home/source/java/target/shopping-service-1.0.0.jar /app/shopping-service-1.0.0.jar
WORKDIR /app

ENV KAFKA_SERVER_NAME=kafka
ENV KAFKA_SERVER_PORT=9092

CMD ["java", "-jar", "shopping-service-1.0.0.jar"]
