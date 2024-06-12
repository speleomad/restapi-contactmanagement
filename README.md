# REST API Contact Management

## Description

Backend Contact Management App is a Spring Boot application for managing contacts. It provides RESTful endpoints to create, read, update, and delete contact information.

## Usage
  
The application will be accessible at `http://localhost:8085`.

## Dependencies

- **Spring Boot Starter Parent**: `3.3.0`
- **Spring Boot Starter Data JPA**
- **Spring Boot Starter Validation**
- **Spring Boot Starter Web**
- **Spring Boot DevTools**
- **Project Lombok**
- **Spring Boot Starter Test**
- **MySQL Connector Java**

## Configuration

The application uses MySQL as the database. Configure the `application.properties` file with your database settings:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
