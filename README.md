# 🛒 E-Commerce Backend Web Application

A scalable and secure RESTful E-Commerce Backend built using **Spring Boot**, **Spring Security**, **JWT Authentication**, **Hibernate/JPA**, and **MySQL**.

This project follows a layered architecture and implements role-based access control (RBAC) to provide secure access to resources such as users, products, categories, and orders.

---

## 🚀 Features

### 🔐 Authentication & Authorization

- User Registration
- User Login
- JWT Token Generation
- JWT Token Validation
- Stateless Authentication
- Role-Based Access Control (RBAC)
- BCrypt Password Encryption

### 👤 User Management

- Create User Accounts
- Retrieve User Information
- Update User Details
- Delete Users

### 📦 Product Management

- Create Products
- Update Products
- Delete Products
- Browse Products
- Product Categorization

### 📂 Category Management

- Create Categories
- Retrieve Categories
- Update Categories
- Delete Categories

### 🛍️ Order Management

- Place Orders
- View Orders
- Cancel Orders
- Update Order Status
- Update Payment Status

### ⚠️ Exception Handling

- Global Exception Handling
- Validation Error Handling
- Resource Not Found Handling
- Bad Request Handling

---

## 🛠️ Tech Stack

### Backend

- Java 21+
- Spring Boot 3
- Spring Security 6
- Spring Data JPA
- Hibernate ORM

### Database

- MySQL

### Authentication

- JWT (JSON Web Token)

### Build Tool

- Maven

### Additional Libraries

- Lombok
- Jakarta Validation
- JJWT

---

## 📁 Project Structure

```text
src/main/java/com/myProject/E_CommerceBackendProject

├── auth
│   ├── controller
│   ├── dto
│   └── service
│
├── category
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── order
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── product
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── user
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── security
│   ├── jwt
│   │   ├── JwtService
│   │   └── JwtAuthFilter
│   │
│   └── user
│       └── CustomUserDetailsService
│
├── config
│   └── SecurityConfig
│
├── exception
│   ├── ApiError
│   ├── BadRequestException
│   ├── ResourceNotFoundException
│   └── GlobalExceptionHandler
│
└── ECommerceBackendProjectApplication
```

---

## 👥 Roles

```java
ROLE_ADMIN
ROLE_USER
ROLE_SELLER
```

### ROLE_ADMIN

- Manage all users
- View all orders
- Manage order statuses
- Access administrative endpoints

### ROLE_USER

- Browse products
- Place orders
- Cancel orders

### ROLE_SELLER

- Create products
- Update products
- Delete products
- Manage order statuses

---

# 🔐 Authentication

## Register User

### Endpoint

```http
POST /api/v1/auth/register
```

### Request Body

```json
{
  "name": "John Kent",
  "email": "johnkent@gmail.com",
  "password": "abcdef",
  "paymentMethod": "UPI"
}
```

### Response

```http
204 No Content
```

---

## Login User

### Endpoint

```http
POST /api/v1/auth/login
```

### Request Body

```json
{
  "email": "johnkent@gmail.com",
  "password": "abcdef"
}
```

### Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## Using JWT Token

Include the token in the Authorization header:

```http
Authorization: Bearer <jwt-token>
```

Example:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

# 🔒 Security Architecture

### JWT Flow

1. User logs in using email and password.
2. Spring Security authenticates credentials.
3. JWT token is generated.
4. Client stores JWT token.
5. Client sends token in Authorization header.
6. `JwtAuthFilter` validates token.
7. Spring Security populates Security Context.
8. Protected endpoints become accessible according to roles.

---

# ⚙️ Configuration

## application.properties

```properties
server.port=5048
server.servlet.context-path=/api/v1

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

# ▶️ Running the Project

## Clone Repository

```bash
git clone https://github.com/your-username/ecommerce-backend.git
```

## Navigate to Project

```bash
cd ecommerce-backend
```

## Run Application

### Windows

```bash
./mvnw spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

Application will start at:

```text
http://localhost:5048/api/v1
```

---

# 📋 Sample Protected Request

### Get Products

```http
GET /api/v1/products
Authorization: Bearer <jwt-token>
```

---

# ❌ Error Handling

The project uses centralized exception handling through:

```java
@RestControllerAdvice
```

### Example Error Response

```json
{
  "status": 404,
  "message": "User not found",
  "timestamp": "2026-06-30T18:30:00"
}
```

### Supported Error Types

- ResourceNotFoundException
- BadRequestException
- Validation Errors
- Authentication Errors
- Internal Server Errors

---

# 🧪 Validation

Example:

```java
@NotBlank(message = "Email is a required field")
private String email;

@NotBlank(message = "Password is a required field")
@Size(min = 6, message = "Password must have at least 6 characters")
private String password;
```

---

# 🔑 Security Features

- JWT Authentication
- JWT Authorization
- BCrypt Password Hashing
- Stateless Sessions
- Custom Authentication Filter
- Spring Security Integration
- Role-Based Access Control

---

# 🎯 Learning Outcomes

This project demonstrates:

- Spring Boot Development
- REST API Design
- Spring Security
- JWT Authentication
- Hibernate & JPA
- MySQL Integration
- Exception Handling
- Validation
- Layered Architecture
- Enterprise Backend Development

---

# 🚧 Future Improvements

- Refresh Tokens
- Email Verification
- Password Reset
- Shopping Cart
- Wishlist
- Product Images
- Swagger/OpenAPI Documentation
- Docker Support
- Redis Caching
- Unit Testing
- Integration Testing
- CI/CD Pipeline
- Payment Gateway Integration
- Inventory Management

---

# 👨‍💻 Author

**Amaan Shakil Coatwala**

Backend Developer specializing in:

- Java
- Spring Boot
- Spring Security
- REST APIs
- SQL Databases
- Backend Architecture

---

# ⭐ If you found this project useful

Consider giving the repository a star to support the project.
