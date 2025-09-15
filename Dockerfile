FROM openjdk:21-jdk-slim
COPY build/libs/back-0.0.1-SNAPSHOT.jar.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080