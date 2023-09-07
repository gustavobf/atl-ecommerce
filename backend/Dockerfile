FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/commerce-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} commerce-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/commerce-0.0.1-SNAPSHOT.jar"]