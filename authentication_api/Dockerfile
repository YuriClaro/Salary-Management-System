FROM openjdk:21-jdk

WORKDIR /app

COPY build/libs/authentication_api-0.0.1-SNAPSHOT.jar /app/AuthenticationApiApplication.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "AuthenticationApiApplication.jar"]