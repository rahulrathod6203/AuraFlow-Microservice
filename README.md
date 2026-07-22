# AuraFlow Microservices

AuraFlow is a Spring Boot microservices-based backend application demonstrating modern microservice architecture with authentication, service discovery, and user management.

---

## 🏗️ Architecture

```
                   +------------------+
                   |      Client      |
                   +--------+---------+
                            |
          +-----------------+------------------+
          |                 |                  |
          v                 v                  v
 User Auth Service   User Profile Service   Period Log Service
      (9091)              (9092)               (9093)
              \            |            /
               \           |           /
                +----------+----------+
                           |
                  Service Registry
                      Eureka (8761)
```

---

## 📦 Microservices

### 1. Service Registry

- Spring Cloud Eureka Server
- Registers all microservices
- Enables service discovery

**Port:** `8761`

---

### 2. User Authentication Service

Responsible for:

- User Registration
- User Login
- JWT Token Generation
- Password Encryption
- Authentication APIs

**Tech Stack**

- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- Spring Data JPA

**Port:** `9090`

---

### 3. User Profile Service

Responsible for:

- Managing user profile details
- Updating user information
- Fetching user profile

**Port:** `9091`

---

### 4. Period Log Service

Responsible for:

- Create Period Logs
- View Period Logs
- Update Period Logs
- Delete Period Logs
- Admin Endpoints

**Port:** `9092`

---

## 🚀 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Cloud Netflix Eureka
- Spring Data JPA
- PostgreSQL
- JWT Authentication
- Maven
- Docker
- AWS

---

## 🔐 Authentication Flow

```
Client
   |
   | Login
   v
User Auth Service
   |
   | Generates JWT Token
   v
Client stores JWT
   |
   | Authorization: Bearer <token>
   v
Protected Microservices validate the JWT before processing requests
```

---

## 🛠️ Running the Project

### Clone the Repository

```bash
git clone https://github.com/rahulrathod6203/AuraFlow-Microservice.git
```

### Start the Services

Start the services in the following order:

1. Service Registry
2. User Authentication Service
3. User Profile Service
4. Period Log Service

---

## 📁 Project Structure

```
AuraFlow-Microservice
│
├── service-registry
├── user-auth-service
├── user-profile-servcie
├── period-log-service
└── README.md
```

---

## 🔮 Future Enhancements

- API Gateway
- Docker
- Docker Compose
- Kubernetes
- Redis Cache
- Config Server
- Centralized Logging
- Distributed Tracing
- GitHub Actions CI/CD
- AWS Deployment

---

## 👨‍💻 Author

**Rahul Rathod**

Software Engineer

GitHub:  
https://github.com/rahulrathod6203

---

## ⭐ Support

If you found this project helpful, consider giving it a ⭐ on GitHub.
