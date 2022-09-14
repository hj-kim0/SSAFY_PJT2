FROM openjdk:17-jdk-alpine
ADD /build/libs/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","/app.jar"]