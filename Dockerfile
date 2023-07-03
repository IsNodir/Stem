FROM openjdk:19-alpine
WORKDIR /app
COPY --from=build /app/target/Stem-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]