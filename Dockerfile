FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/*.jar stem.jar
ENTRYPOINT ["java","-jar","/stem.jar"]
EXPOSE 8080

