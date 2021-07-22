FROM openjdk:11-jdk-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod", "/app.jar"]