FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package

FROM openjdk:17-alpine
COPY --from=build /target/*.jar stem.jar
ENTRYPOINT ["java","-jar","/stem.jar"]
EXPOSE 8080

