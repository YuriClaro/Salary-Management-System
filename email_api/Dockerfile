FROM openjdk:21-jdk

WORKDIR /app

COPY build/libs/email_api-0.0.1-SNAPSHOT.jar /app/EmailApiApplication.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "EmailApiApplication.jar"]