# E-Commerce RESTful Engine (Backend)

A production-grade, high-performance e-commerce backend engine built using **Java 21**, **Spring Boot**, and **MySQL**. This system transitions beyond basic CRUD layouts by orchestrating complex data persistence lifecycles, advanced bidirectional relational mapping, dynamic inventory state management, and strict transactional isolation patterns.

---

## 🚀 Key Architectural Features

* **Advanced Relational Domain Modeling:** Promoted the traditional multi-to-multi product basket association into a standalone **Associative Bridge Entity (`CartItem`)**. This enables high-performance tracking of product-wise quantities and granular real-time subtotals while entirely eliminating redundant database rows.
* **Dynamic Inventory & Basket State Engine:** Structured an automated checkout lifecycle using Java **Streams** and the **Optional** API to process atomic quantity increments, reductions, and clean cascade-driven row evictions (`orphanRemoval = true`) when product counters drop to zero.
* **Strict AOP Transactional Boundaries:** Defended ledger integrity against `LazyInitializationException` errors and partial database updates by binding critical service layer processes with Spring **Context Management Framework (`@Transactional`)**.
* **Enterprise Network Optimization:** Built controller endpoint interactions to return a complete structural `CartDto` payload (`200 OK`) on modifications rather than generic empty bodies (`204 No Content`), minimizing network latency by preventing chatty frontend round-trips.
* **Defensive Edge-Case Filtering:** Hardened API resilience via a centralized global exception wrapper (`@RestControllerAdvice`) linked with custom validation triggers (`ResourceNotFoundException`, `BadRequestException`) for rigid error payload formatting.

---

## 🛠️ Tech Stack & Ecosystem

* **Core Platform:** Java 21, Spring Boot 3.5.x
* **Data Layer:** Spring Data JPA, Hibernate ORM, MySQL
* **Tools & Building:** Apache Maven, Thunder Client, VS Code, Lombok

---

## 📂 Domain Directory Tree Structure

The project follows a modular, feature-oriented package structure to maximize domain isolation and ensure micro-service extensibility:

```text
com.myProject.E_CommerceBackendProject
├── cart            # Basket lifecycles, quantity engines, and atomic clear actions
├── category        # Product classification domain logic
├── config          # Global system settings and security filters
├── exception       # @RestControllerAdvice interceptors and custom API exceptions
├── payment         # Bank ledger tracking and balance validation services
├── product         # Catalog search filtering and stock inventory metrics
└── user            # User profile structures and identity endpoints

## 🔌 API Documentation & Endpoint Directory

Every endpoint routes through the global context prefix layout: `/api/v1`

### 📦 Product Catalog Module
* `GET    /api/v1/products` — Retrieve a paginated collection of products (`Pageable`).
* `GET    /api/v1/products/{id}` — Lookup a targeted product by its structural identifier.
* `GET    /api/v1/products/search?name={}&price={}` — Filter products by naming keywords and maximum cost limits.
* `POST   /api/v1/products` — Register a brand-new inventory product entity into the system database.

### 🛒 Cart Management Module
* `GET    /api/v1/carts/{userId}` — Fetch the current state, items, and calculated totals for a user's basket.
* `POST   /api/v1/carts/items` — Atomically increment or decrement product-wise line quantities via payload body parameters.
* `DELETE /api/v1/carts/{userId}/items/{productId}` — Evict a distinct sub-resource row association from the basket layout immediately (`200 OK` with updated Cart layout).
* `DELETE /api/v1/carts/{userId}/clear` — Purge all item allocations cleanly while retaining parent record structures (`204 No Content`).

---

## ⚡ Setup, Build, and Execution Sequence

### Prerequisites
* Java 21 JDK or higher
* MySQL Server running locally

### 1. Database Configuration
Create a fresh MySQL schema instance:
```sql
CREATE DATABASE ecommerce_db;
