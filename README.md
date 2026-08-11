# 🏥 Hospital Management System API

A robust, production-ready RESTful API built with **Spring Boot 3** and **Java 21**. This project demonstrates a professional **Layered Architecture** for managing hospital resources (Patients, Doctors, and Appointments) with a focus on clean code, separation of concerns, and safe data handling.

## 🛠 Tech Stack
* **Language:** Java 21
* **Framework:** Spring Boot 3.3.2
* **Database:** PostgreSQL (via Spring Data JPA / Hibernate)
* **Build Tool:** Gradle
* **API Documentation:** Springdoc OpenAPI (Swagger UI)

## 🏗 Architecture & Project Structure

This project intentionally moves beyond a simple "fat controller" approach and implements a strict **3-Tier Layered Architecture** to ensure scalability and testability.

```text
src/main/java/com/example/hospital/
├── config/       # Application-wide configurations (e.g., Swagger/OpenAPI setup)
├── controller/   # The Presentation Layer (Handles HTTP requests/responses)
├── service/      # The Business Logic Layer (Contains rules and orchestration)
├── repo/         # The Data Access Layer (Spring Data JPA repositories)
├── model/        # JPA Entities (Database tables and relationships)
└── dtos/         # Data Transfer Objects (Shapes data for safe API transport)
