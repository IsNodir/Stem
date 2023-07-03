FROM openjdk:19
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} stem.jar
ENTRYPOINT ["java","-jar","/stem.jar"]

