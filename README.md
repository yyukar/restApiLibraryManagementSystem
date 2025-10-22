
<p align="center">
  <img src="https://picsum.photos/200" width="200" height="165" style="border-radius: 15px;">
</p>

<h3 align="center">Library Management System (Spring Boot REST API)</h3>

<p align="center">
  A modular, production-ready REST API built with Spring Boot and PostgreSQL for managing library operations.  
  <br>
  <a href="#quick-start"><strong>Explore the documentation »</strong></a>
</p>

---

## Project Overview

This Library Management System provides a complete CRUD-based REST API to manage library resources such as **books, authors, publishers, categories, and borrowings**.  
It enforces real-world business rules like:

- Preventing deletion of categories that have books
- Validating stock before lending books
- Returning a book automatically increases stock
- Restricting borrower email updates
- Hiding publisher address in GET responses

The project demonstrates **layered architecture**, **DTO validation**, **service-level business logic**, and **Swagger-documented REST endpoints** — perfect for full-stack Java learners and backend developers practicing clean Spring Boot design.

---

## Table of Contents

- [Quick Start](#quick-start)
- [Status](#status)
- [What’s Included](#whats-included)
- [Technologies Used](#technologies-used)
- [Key Features](#key-features)
- [Endpoints](#endpoints)
- [Thanks](#thanks)
- [License](#license)

---

## Quick Start

> This is a Spring Boot REST API using PostgreSQL.  
> Requires JDK 21+ and Maven 3.9+.

### 1️⃣ Clone the repository

```bash
git clone https://github.com/yyukar/library-management-system.git
cd Library-Management-System
```

### 2️⃣ Configure the database

Create a PostgreSQL database named `library_db`.

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.application.name=Library-Management-System
```

### 3️⃣ Run the application

```bash
mvn spring-boot:run
```

Open **http://localhost:8080/swagger-ui.html**  
You can explore and test all endpoints via the Swagger UI.

---

## Status

[![Language](https://img.shields.io/badge/language-Java_21-blue)]()  
[![Framework](https://img.shields.io/badge/framework-Spring_Boot_3.5.6-green)]()  
[![Database](https://img.shields.io/badge/database-PostgreSQL-orange)]()  
[![Status](https://img.shields.io/badge/status-stable-brightgreen)]()

---

## What’s Included

<details>
<summary><strong>Click to view project structure</strong></summary>

```
library-management-system/
│
├── src/main/java/com/example/library/
│   ├── LibraryManagementSystemApplication.java
│   ├── config/                  # ModelMapper & Swagger configuration
│   ├── core/                    # Exception handling & API response wrappers
│   ├── domain/entity/           # JPA entities (Author, Book, Publisher, etc.)
│   ├── repository/              # JPA repositories
│   ├── service/
│   │   ├── interfaces/          # Service interfaces
│   │   └── impl/                # Business logic implementations
│   └── web/
│       ├── controller/          # REST controllers
│       └── dto/                 # Request & response DTOs
│
├── src/main/resources/
│   └─── application.properties  # Database & app configuration
│
└── pom.xml                      # Maven dependencies
```

</details>

---

## Technologies Used

| Technology | Purpose |
|-------------|----------|
| **Java 21** | Core language |
| **Spring Boot 3.5.6** | Application framework |
| **Spring Data JPA** | ORM & repository abstraction |
| **PostgreSQL** | Relational database |
| **Lombok** | Boilerplate reduction |
| **ModelMapper** | Entity–DTO mapping |
| **Hibernate Validator** | Request validation |
| **Springdoc OpenAPI (Swagger)** | API documentation |
| **Maven** | Build & dependency management |

---

## Key Features

- **CRUD Operations** for all entities: Author, Publisher, Category, Book, and Book Borrowing
- **Business Rules**
    - Cannot delete a category that contains books
    - Book stock automatically decreases on borrow
    - Returning a book sets return date and increases stock
    - Borrower email cannot be modified after creation
    - Publisher’s address excluded from GET responses
- **DTO Validation** using `@Valid` and `jakarta.validation` annotations
- **Global Exception Handling** with custom messages
- **Layered Architecture**: Controller → Service → Repository → Entity
- **Swagger UI** integration for testing and exploration

---

## Endpoints

| Method | Path | Description | Body (DTO) | Success |
|--------|------|-------------|-------------|----------|
| POST | `/api/authors` | Create Author | `AuthorRequest` | 201 |
| GET | `/api/authors` | List Authors | - | 200 |
| GET | `/api/authors/{id}` | Get Author | - | 200 |
| PUT | `/api/authors/{id}` | Update Author | `AuthorRequest` | 200 |
| DELETE | `/api/authors/{id}` | Delete Author | - | 200 |
| POST | `/api/publishers` | Create Publisher | `PublisherRequest` | 201 |
| GET | `/api/publishers` | List Publishers | - | 200 |
| GET | `/api/publishers/{id}` | Get Publisher (no address) | - | 200 |
| PUT | `/api/publishers/{id}` | Update Publisher | `PublisherRequest` | 200 |
| DELETE | `/api/publishers/{id}` | Delete Publisher | - | 200 |
| POST | `/api/categories` | Create Category | `CategoryRequest` | 201 |
| GET | `/api/categories` | List Categories | - | 200 |
| GET | `/api/categories/{id}` | Get Category | - | 200 |
| PUT | `/api/categories/{id}` | Update Category | `CategoryRequest` | 200 |
| DELETE | `/api/categories/{id}` | Delete Category (guarded) | - | 200 / 409 |
| POST | `/api/books` | Create Book | `BookRequest` | 201 |
| GET | `/api/books` | List Books | - | 200 |
| GET | `/api/books/{id}` | Get Book | - | 200 |
| PUT | `/api/books/{id}` | Update Book | `BookUpdateRequest` | 200 |
| DELETE | `/api/books/{id}` | Delete Book | - | 200 |
| POST | `/api/borrowings` | Create Borrowing (stock--) | `BookBorrowingCreateRequest` | 201 |
| GET | `/api/borrowings` | List Borrowings | - | 200 |
| GET | `/api/borrowings/{id}` | Get Borrowing | - | 200 |
| PUT | `/api/borrowings/{id}` | Update Borrowing (no email) | `BookBorrowingUpdateRequest` | 200 |
| PATCH | `/api/borrowings/{id}/return` | Return Book (stock++) | - | 200 |

---

## Thanks

This project was created with the support of [Patika.dev](https://www.patika.dev) as part of the **Fullstack Java Developer Bootcamp**.  
Special thanks to the instructors and community contributors who guided this learning journey.

---

## License

This project is currently unlicensed.  
You are free to use, modify, and learn from the code.