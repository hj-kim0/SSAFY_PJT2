FROM adoptopenjdk:11-jdk AS builder
WORKDIR /var/jenkins_home/workspace/Release/backend
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM adoptopenjdk:11-jdk
COPY --from=builder build/libs/*.jar app.jar

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app.jar"]