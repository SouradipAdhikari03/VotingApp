﻿# 🗳️ VotingApp (Backend)

**Online Voting Application**

---

## 📌 Description

This is a backend project for an online voting system built with **Java Spring Boot**. It’s secured using **JWT** and **BCrypt**, and offers features like voter/candidate management, vote casting, result declaration, and admin controls.

---

## ✅ Features

* **User Management**

    * **Voters** can register, update, delete profiles.
    * **Candidates** can register, update, delete profiles.
    * Data validation ensures:

        * No duplicate email registrations.
        * Candidates register for only one party; each party has only one candidate.
* **Security**

    * Passwords are stored hashed using **BCrypt**.
    * Uses **JWT** for stateless authentication.
    * Prevents multiple voting: one vote per voter.
* **Voting**

    * Registered voters can cast their vote.
* **Results**

    * Everyone can view election results.
    * **Admin** can declare results.
    * Result data includes vote ID, winner ID, total votes, votes received by the winner, and election name (e.g. "Loksabha").
* **Role-based authentication** — Coming soon!

---

## 💠 Technology Stack

* Java 21
* Spring Boot 3
* Spring Data JPA
* PostgreSQL (or any SQL database)
* JWT for authentication
* BCrypt for password hashing
* Maven for build management
* Lombok (optional)

---

## 🚀 Getting Started

### 1. Setup & Clone

```bash
git clone https://github.com/SouradipAdhikari03/VotingApp.git
cd VotingApp
```

### 2. Configure Database (PostgreSQL example)

In `src/main/resources/application.properties`, update:

```properties
spring.application.name=VotingApp

# PostgreSQL DB Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/<DB_NAME>
spring.datasource.username=postgres
spring.datasource.password=<YOUR_PASSWORD>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
logging.level.org.springframework.security=TRACE

server.servlet.session.cookie.same-site=strict
```

### 3. Build & Run

* Open in your IDE and **enable Lombok annotations**.
* Load `pom.xml` and let Maven download dependencies.
* Run the Spring Boot application via your IDE or:

```bash
mvn spring-boot:run
```

---


## 🗂️ Project Directory Structure

```
VotingApp/
├── .idea/                       # IntelliJ IDEA project files
├── target/                      # Compiled bytecode and build files
├── VotingApp/
│   ├── mvn/                     # Maven wrapper
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com.vote.VotingApp/
│   │   │   │       ├── Configuration/
│   │   │   │       │   ├── Config.java
│   │   │   │       │   └── JWTFilter.java
│   │   │   │       ├── Controller/
│   │   │   │       │   ├── CandidateController.java
│   │   │   │       │   ├── ElectionResultController.java
│   │   │   │       │   ├── VoterController.java
│   │   │   │       │   └── VotingController.java
│   │   │   │       ├── DTO/
│   │   │   │       │   ├── ElectionResultDTO/
│   │   │   │       │   │   ├── ElectionResultRequestDTO.java
│   │   │   │       │   │   └── ElectionResultResponseDTO.java
│   │   │   │       │   ├── VoteDTO/
│   │   │   │       │   │   └── VoteRequestDTO.java
│   │   │   │       │   └── VoterDTO/
│   │   │   │       │       ├── VoterLoginDTO.java
│   │   │   │       │       ├── VoterRequestDTO.java
│   │   │   │       │       └── VoterResponseDTO.java
│   │   │   │       ├── Entity/
│   │   │   │       │   ├── Candidate.java
│   │   │   │       │   ├── ElectionResult.java
│   │   │   │       │   ├── UserPrincipal.java
│   │   │   │       │   ├── Vote.java
│   │   │   │       │   └── Voter.java
│   │   │   │       ├── Exception/
│   │   │   │       │   ├── DuplicateResourceException.java
│   │   │   │       │   ├── ErrorResponse.java
│   │   │   │       │   ├── GlobalExceptionHandler.java
│   │   │   │       │   ├── ResourceNotFoundException.java
│   │   │   │       │   └── VoteNotAllowedException.java
│   │   │   │       ├── Repo/
│   │   │   │       │   ├── CandidateRepo.java
│   │   │   │       │   ├── ElectionResultRepo.java
│   │   │   │       │   ├── UserRepo.java
│   │   │   │       │   ├── VoteRepo.java
│   │   │   │       │   └── VoterRepo.java
│   │   │   │       ├── Service/
│   │   │   │       │   ├── CandidateService.java
│   │   │   │       │   ├── ElectionResultService.java
│   │   │   │       │   ├── JWTService.java
│   │   │   │       │   ├── MyUserDetailsService.java
│   │   │   │       │   ├── VoterService.java
│   │   │   │       │   └── VotingService.java
│   │   │   │       └── VotingAppApplication.java
│   │   └── resources/           # application.properties, static files
│   └── test/                    # Test cases (if any)
├── .gitignore
├── pom.xml                      # Maven project configuration
└── README.md
```





## 📁 Project Structure (REST Endpoints)

| Module     | Method | Endpoint                                | Description                           |
|------------|--------|------------------------------------------|---------------------------------------|
| 🔐 Auth    | POST   | `/api/voters/login`                      | Login with JWT                        |
| 👤 Voters  | POST   | `/api/voters/register`                   | Register a voter                      |
|            | GET    | `/api/voters/all`                        | Get all voters                        |
|            | GET    | `/api/voters/{id}`                       | Get voter by ID                       |
|            | PUT    | `/api/voters/update/{id}`               | Update voter                          |
|            | DELETE | `/api/voters/delete/{id}`               | Delete voter                          |
| 🎯 Candidate | POST | `/api/candidate/register`               | Register a candidate                  |
|            | GET    | `/api/candidate/all`                     | Get all candidates                    |
|            | GET    | `/api/candidate/{id}`                    | Get candidate by ID                   |
|            | PUT    | `/api/candidate/update/{id}`            | Update candidate                      |
|            | DELETE | `/api/candidate/delete/{id}`            | Delete candidate                      |
| 🗳️ Voting  | POST   | `/api/votes/cast`                        | Cast a vote (authenticated)           |
|            | GET    | `/api/votes/all`                         | Get all votes                         |
| 🏆 Results | POST   | `/api/election-result/declare`           | Declare election result (admin only)  |
|            | GET    | `/api/election-result/get-all`          | Get all election results              |


## 👨‍💼 Author & Contributions

This backend was fully designed and implemented by **Souradip Adhikari**.
Use tools like **Postman** to explore and test the API endpoints.

---

## 📍 Last Updated

*Last updated on: **2025-06-16***

---

Feel free to fork this project, contribute, or extend functionality.

> ✨ Role-based authentication coming soon. Stay tuned!
