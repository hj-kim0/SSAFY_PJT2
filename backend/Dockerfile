FROM openjdk:11-jdk-alpine
WORKDIR /var/jenkins_home/workspace/Release/backend
ADD /build/libs/project-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","/app.jar"]