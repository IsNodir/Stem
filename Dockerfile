FROM openjdk:19
ARG JAR_FILE=/target/*.jar
COPY /target/Stem-0.0.1-SNAPSHOT.jar /stem.jar
ENTRYPOINT ["java","-jar","/stem.jar"]