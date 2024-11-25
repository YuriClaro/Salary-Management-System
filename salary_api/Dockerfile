FROM openjdk:21-jdk

WORKDIR /app

COPY build/libs/salary_api-0.0.1-SNAPSHOT.jar /app/SalaryApiApplication.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "SalaryApiApplication.jar"]