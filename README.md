# 🚗 Car Rental System - Spring Boot API

This project is a multi-role car rental system built with Spring Boot. The system includes three main user roles: *Admin, **Company, and **Customer*. Each role has its own specific permissions and responsibilities. Authentication and authorization are handled using JWT (JSON Web Token).

---

## 📁 Project Structure

src/
├── core/                 # Shared classes and infrastructure
│   ├── exceptions/
│   ├── security/
│   └── utilities/
│
├── modules/              # Domain modules
│   ├── auth/             # Authentication and registration logic
│   ├── admin/
│   ├── company/
│   ├── customer/
│   └── rental/           # Rental-related logic
│
├── entities/             # Entity classes (Company, Customer, Role, Rental, etc.)
└── repositories/         # JpaRepository interfaces

---

## 👥 Roles & Responsibilities

| Role      | Permissions                                                                 |
|-----------|------------------------------------------------------------------------------|
| ADMIN   | Full control over workers and invoices, can manage all entities             |
| COMPANY | Can add/update cars, brands, models, fuel types                             |
| CUSTOMER| Can rent cars and select pickup and drop-off locations                      |

---

## 🛡️ Authentication

- JWT-based authentication
- Spring Security for endpoint protection
- Role verification through UserDetailsServiceImpl

---

## 🔐 Authorization (Spring Security)

### Token Structure:
The JWT contains email, id, role, etc.

### Security Rules:
- /api/auth/** → Public access (register, login)
- /api/admin/** → ADMIN only
- /api/company/** → COMPANY and ADMIN
- /api/customer/** → CUSTOMER only

### Method-level Authorization Example:
```java
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<?> deleteUser(@PathVariable int id) { ... }

@PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
public ResponseEntity<?> addCar(@RequestBody CreateCarRequest req) { ... }

@PreAuthorize("hasRole('CUSTOMER')")
public ResponseEntity<?> rentCar(@RequestBody CreateRentalRequest req) { ... }

🔧 Tech Stack
	•	Java 17+
	•	Spring Boot 3.x
	•	Spring Security
	•	Spring Data JPA
	•	PostgreSQL / MySQL (selectable)
	•	JWT (JSON Web Tokens)
	•	Lombok
	•	ModelMapper

✅ Features
	•	Role-based authentication
	•	JWT login system
	•	Full rental workflow
	•	Vehicle and location management
	•	Invoice creation (for both company and customer)
	•	Worker and maintenance management (for admin)
	•	Global exception and validation handling

📌 Tips
	•	Use @AllArgsConstructor and @Service for dependency injection
	•	Use ModelMapper for mapping between DTOs and entities
	•	Implement BusinessRules for domain-level validations
	•	Centralized error handling for consistency

⸻

🧩 Future Enhancements
	•	Payment integration
	•	Frontend user panel (React/Angular)
	•	Email verification
	•	Multi-location support
