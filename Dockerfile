FROM eclipse-temurin:17-jdk-alpine
COPY /Users/nodir/IdeaProjects/Stem/target/*.jar stem.jar
ENTRYPOINT ["java","-jar","/stem.jar"]
EXPOSE 8080

