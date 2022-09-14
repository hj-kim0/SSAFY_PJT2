FROM adoptopenjdk/openjdk11
CMD ["./mvnw", "clean", "package"]
RUN gradlew build
ARG JAR_FILE_PATH=build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar
CMD ["java", "-jar", "app.jar"]