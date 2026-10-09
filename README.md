# Student Management System

A backend application built using Spring Boot to manage students and courses through REST APIs.

## Features

* Create, view, update, and delete students
* Create, view, update, and delete courses
* Associate students with courses
* Validate request data
* Prevent duplicate student email addresses
* Handle exceptions with appropriate HTTP status codes
* Pagination and sorting
* Search students by name
* Retrieve students based on age using database queries

## Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Database:** PostgreSQL
* **ORM:** Spring Data JPA / Hibernate
* **Build Tool:** Maven
* **API Testing:** Postman
* **IDE:** IntelliJ IDEA

## Project Structure

```text
student-management/
├── src/main/java/student_management/
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── exception/
│   ├── repository/
│   └── service/
├── src/main/resources/
│   └── application.properties
├── pom.xml
└── README.md
```

## Setup Instructions

### 1. Prerequisites

Install Java, Maven (if not using the Maven Wrapper), and PostgreSQL.

### 2. Create the Database

Create a PostgreSQL database named:

```sql
CREATE DATABASE studentdb;
```

### 3. Configure the Database

Update `src/main/resources/application.properties` with your local PostgreSQL connection settings.

**Security:** Do not commit database passwords, API keys, or other secrets to GitHub.

### 4. Run the Application

From the project root, run:

```bash
./mvnw spring-boot:run
```

On Windows, use:

```powershell
.\mvnw.cmd spring-boot:run
```

If the Maven Wrapper is unavailable, run the application from IntelliJ IDEA or use an installed Maven setup.

## API Overview

The application provides REST endpoints for managing students and courses.

Typical operations include:

* `POST` — Create a student or course
* `GET` — Retrieve students or courses
* `PUT` — Update an existing student or course
* `DELETE` — Delete a student or course

Use Postman to send requests to the running application. Check the controller classes for the exact endpoint paths and request formats.

## Error Handling

The application handles errors such as:

* Student or course not found
* Duplicate student email
* Invalid request data
* Database constraint violations

Appropriate HTTP status codes are returned for handled errors.

## Future Improvements

* Add authentication and role-based authorization
* Add automated unit and integration tests
* Deploy the application to a cloud platform
* Improve API documentation using Swagger/OpenAPI

## Author

**Rajnish Rai**
