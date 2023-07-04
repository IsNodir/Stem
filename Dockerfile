FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar stem.jar
ENTRYPOINT ["java","-jar","/stem.jar"]
EXPOSE 8080

