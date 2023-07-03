FROM openjdk:19-alpine
WORKDIR /app
COPY /target/Stem-0.0.1-SNAPSHOT.jar /stem.jar
ENTRYPOINT ["java","-jar","/stem.jar"]