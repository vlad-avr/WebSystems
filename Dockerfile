FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD target/lab1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
