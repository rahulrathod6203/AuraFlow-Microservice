# AuraFlow Wellness Platform

A production-style, high-performance menstrual wellness tracking application built using a decoupled layered architecture. This platform is engineered with a heavy focus on backend resilience, robust security standards, database optimization, and modern DevOps practices.

> 🚧 **Status:** Under Active Development  
> 🎯 **Current Focus:** Implementing Transactional Logging & Symptom Analytics Engines

---

## 🚀 Key Architectural Highlights & Senior-Level Practices

* **Decoupled Layered Architecture:** Strict separation of concerns across Controllers, Services, Repositories, and DTO layers.
* **Advanced Security:** Stateless authentication managed via Spring Security and JWT, utilizing custom principal mapping to eliminate ID spoofing vulnerabilities.
* **Database Optimizations:** Designed with PostgreSQL, leveraging composite indexing on historical transactional data blocks to ensure scalable $O(\log N)$ query performance.
* **Type-Safe Mapping:** Implemented MapStruct for high-performance, compilation-time DTO-to-Entity conversions, keeping domain structures completely hidden from the presentation layer.
* **Production DevOps Pipeline:** Fully containerized via Docker, deployed to AWS EC2 infrastructure, with the frontend application securely distributed via AWS S3.

---

## 🛠️ Tech Stack

* **Backend:** Java 21, Spring Boot 4, Spring Security, Spring Data JPA
* **Database:** PostgreSQL
* **Frontend:** React
* **Security:** JSON Web Tokens (JWT), BCrypt
* **Containerization & DevOps:** Docker, AWS EC2, AWS S3
* **API Documentation:** Swagger / OpenAPI

---

## 📂 System Architecture & Package Directory

```text
src/main/java/com/flowsense
├── config         # Infrastructure, Database Auditing & Security Configurations
├── controller     # REST API Gatekeepers (HTTP binding & JSR-303 validations)
├── service        # Core Domain Orchestration & Transactional Boundaries
├── repository     # Data Access Layer with optimized composite index querying
├── dto            # Immutable Request/Response Data Transfer Objects
├── entity         # Relational database models & mapping rules
├── mapper         # MapStruct compile-time object conversion interfaces
├── security       # JWT Custom Filters, Token Utilities & UserDetails
├── exception      # Global Exception Advice Handling & Custom Domain Errors
└── util           # Shared Application Helper Utilities
```
## 📋 Core Features & Development Roadmap

### ✅ Phase 1: Core Foundation & Infrastructure (Completed)
* [x] **Micro-architecture Setup:** Established decoupled package architecture and integrated PostgreSQL database configurations.
* [x] **Secure User Registration:** Implemented secure user onboarding backed by BCrypt password hashing.
* [x] **Stateless Authentication:** Engineered a JWT generation and validation engine with custom Spring Security filter chains.
* [x] **DevOps Containerization:** Dockerized the Spring Boot application and successfully deployed it to an AWS EC2 instance.
* [x] **Frontend Distribution:** Configured and hosted the React client tracking layer on AWS S3 for secure static web serving.

### ⏳ Phase 2: Transactional Logging Engines (In Progress)
* [ ] **Period Logging:** Developing isolated transaction-bounded logic to prevent timeline overlaps and manage continuous cycle states.
* [ ] **Symptom Analytics:** Designing a structured tracking framework supporting multi-variant daily symptoms and multi-tier severity matrices.
* [ ] **Memory Optimization:** Implementing paginated chronological data queries via Spring Data Pageable to safeguard JVM memory.

### 🔮 Phase 3: Advanced Intelligence (Future Scope)
* [ ] **Spring AI Integration:** Building a Natural Language Processing (NLP) parser using LLMs to extract structured logs directly from unstructured user text.
* [ ] **Semantic Vector Search:** Extending PostgreSQL with `pgvector` to enable context-driven semantic search over personal health notes based on user intent rather than literal keywords.

## 🛣️ Core REST API Endpoints

### Authentication & Profiles
* `POST /api/auth/register` — Registers a new user account with encrypted credentials.
* `POST /api/auth/login` — Authenticates user credentials and issues stateless JWT access tokens.
* `GET /api/users/profile` — Retrieves the authenticated profile context directly from the security principal.

### Tracking & Analysis (Active Work)
* `POST /api/periods` — Commits a verified, non-overlapping period log entry to the historical ledger.
* `GET /api/periods/history` — Returns an optimized, paginated timeline of historical cycle trends.
* `POST /api/symptoms` — Logs multi-variant clinical symptoms complete with structured severity metrics.
* `GET /api/dashboard` — Aggregates real-time tracking data to feed frontend analytics dashboards.

---

## 🔧 Getting Started & Local Installation

### Prerequisites
* **Java 21 JDK** (for compiling the Spring Boot 4 backend application)
* **Docker & Docker Compose** (for localized database isolation and micro-service clustering)
* **Node.js** (runtime engine environment for launching the React UI client)
