FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} commerce-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/commerce-0.0.1-SNAPSHOT.jar"]