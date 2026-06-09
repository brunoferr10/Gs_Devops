FROM maven:3.9.9-eclipse-temurin-17-alpine

RUN apk update

RUN adduser -h /home/appuser -s /bin/sh -D appuser

WORKDIR /app

COPY . .

RUN mvn package -DskipTests

RUN chown -R appuser:appuser /app

USER appuser

ENV SERVER_PORT=8080

EXPOSE 8080

CMD ["java","-jar","target/orbital-alert-0.0.1-SNAPSHOT.jar"]