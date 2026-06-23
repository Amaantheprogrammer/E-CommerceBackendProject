# E-Commerce Backend Application

A robust, enterprise-grade RESTful API backend built using **Java** and the **Spring Boot** framework. This application handles core e-commerce business operations, including product catalog management, structural categorization, relational data integrity, and secure client-server data transfer workflows.

## 🚀 Key Features & Implementation Details

*   **Scalable RESTful API Design**: Engineered structured endpoints to cleanly manage operations for a production-ready e-commerce platform.
*   **Decoupled Architecture**: Fully implemented the **Service-Implementation pattern** to split core business logic from controllers, ensuring code maintainability and testability.
*   **Advanced Relational Mappings**: Configured complex object-relational mapping (ORM) data structures using `@OneToMany` and `@ManyToOne` associations to seamlessly link products with categories and users.
*   **Secure Data Transfer**: Leveraged the **DTO (Data Transfer Object)** pattern (`ProductDto`, `NewProductDto`) to filter payload structures and prevent exposing internal database schemas directly to clients.
*   **Robust Request Validation & Exception Handling**: Built global error interceptors (`@ControllerAdvice`) paired with validation constraints to catch API edge cases gracefully and return consistent JSON error responses.

---

## 🛠️ Tech Stack & Ecosystem

*   **Core Language**: Java (JDK 17+)
*   **Framework**: Spring Boot (Spring Web, Spring Data JPA, Spring Security)
*   **ORM / Data Persistence**: Hibernate, JPA
*   **Database**: MySQL
*   **Build Tool & Dependency Management**: Maven
*   **API Testing & Verification**: Postman, ThunderClient

---

## 📂 Project Structure

```text
src/main/java/com/myProject/E_CommerceBackendProject/
│── cart/
│   ├── controller/     
│   ├── dto/           
│   ├── entity/          
│   ├── repository/      
│   └── service/
│── category/
│   ├── controller/     
│   ├── dto/           
│   ├── entity/          
│   ├── repository/      
│   └── service/
│── product/
│   ├── controller/     
│   ├── dto/           
│   ├── entity/          
│   ├── repository/      
│   └── service/   
├── user/
│   ├── controller/      # REST API Controllers (Exposing endpoints)
│   ├── dto/             # Data Transfer Objects (Request/Response payloads)
│   ├── entity/          # JPA Hibernate Domain Models (Database Tables)
│   ├── repository/      # Data access layers (Spring Data JPA)
│   └── service/         # Business Logic Interface & Implementation layers
│
└── ECommerceBackendProjectApplication.java  # Main application entry point
