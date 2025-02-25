# Salary Management System
Development of a salary management system with an architecture composed of 4 microservices orchestrated via Docker. APIs protected with Spring Security and JWT, data auditing with Hibernate Envers, and database versioning with Liquibase. Asynchronous communication via Apache Kafka for sending Excel reports to the authenticated user.

## Microservices Overview
**Authentication API**: Provides robust login and registration mechanisms with JWT token-based authentication to ensure secure user access across all services.

**Salary Management API**: A robust system that allows the creation, reading, updating, and deletion of salaries and salary components, with automatic updates for accepted salary proposals.

**Report Excel API**: A robust system that allows sending Excel reports via email to the authenticated user.

**Portal API**: A robust microservice that accesses the other 3 microservices for user authentication and authorization, managing secure API access based on the user's role.

## Tech Stack
- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security
- JSON Web Token (JWT)
- PostgreSQL
- Apache Kafka
- Spring Cloud OpenFeign
- Java Mail Sender
- Hibernate Envers
- Liquibase
- Docker
- JUnit 5
- Mockito
- Gradlew
- Lombok
- Swagger
- MapStruct

## Architecture
The Salary Management System follows a microservices architecture, with independent services communicating via Kafka for asynchronous communication or synchronous FeignClient calls. Each service is designed for scalability, reliability, and modularity.

## API Documentation
The ``Portal API`` interacts with the``Authentication API``, ``Salary Management API`` and ``Email API`` to handle authentication, salary management, and email functionalities.

## Authentication API
``POST /sign-in``: Authenticates a user and returns a JWT response.

``POST /refreshToken``: Refreshes the JWT token for an authenticated user.

## Salary Management
#### Salary
``POST /api/v1/portal/salary``: Creates a new Salary with the provided details.

``GET /api/v1/portal/salary``: Retrieves a paginated list of all Salaries.

``GET /api/v1/portal/salary/{id}``: Retrieves a Salary by its unique identifier.

``GET /api/v1/portal/salary/search``: Retrieves all salaries based on status and a date range.

``POST /api/v1/portal/salary/proposed/salaryId={id}``: Allows a collaborator to accept a proposed salary decision by providing their decision and the associated salary ID

``POST /api/v1/portal/salary/my-salaries``: Retrieve a paginated list of salaries associated with the authenticated collaborator. Only the collaborator can view their own salaries

``GET /api/v1/portal/salary/collaboratorId={id}``:Retrieves a paginated list of all Salaries associated with a specific collaborator.

``PUT /api/v1/portal/salary/{id}``: Updates a salary by its ID.

``DELETE /api/v1/portal/salary/{id}``: Deletes a salary by its ID.

#### Salary Components

``POST /api/v1/portal/salary/components``: Creates a new salary component.

``GET /api/v1/portal/salary/components``: Retrieves all salaries components.

``GET /api/v1/portal/salary/components/{id}``: Retrieves a salary component by its ID.

``GET /api/v1/portal/salary/components/salaryId={id}``: Retrieves all salaries components based on salary id

``PUT /api/v1/portal/salary/components/{id}``: Updates a salary  component by its ID.

``DELETE /api/v1/portal/salary/components/{id}``: Deletes a salary component by its ID.

## Export Report

`POST /api/v1/portal/export/all/salaries`: Exports all salaries to an external file, triggered by an authorized request. 

`POST /api/v1/portal/export/dates`: Exports all salaries between dates to an external file, triggered by an authorized request 

`POST /api/v1/portal/export/status`: Exports all salaries by status to an external file, triggered by an authorized request

`POST /api/v1/portal/export/collaboratorId={id}`: Exports salary by collaborator ID to an external file, triggered by an authorized request

`POST /api/v1/portal/export/my-salaries`: Exports own salary by user authenticated to an external file, triggered by an authorized request
