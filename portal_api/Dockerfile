FROM openjdk:21-jdk

WORKDIR /app

COPY build/libs/portal_api-0.0.1-SNAPSHOT.jar /app/PortalApiApplication.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "PortalApiApplication.jar"]