# Quiz App with Microservices Architecture

This is a microservices-based **Quiz Application** developed using Spring Boot. The application is divided into multiple services, each performing a specific task. The microservices architecture ensures modularity, scalability, and maintainability.

---

## Features
- **Microservices Architecture:** Each service has its own database and dedicated functionality.
- **Independent Databases:**  
  - `Quiz Service` uses **quizdb** to store quiz information.
  - `Question Service` uses **questiondb** to store questions and their answers.
- **Service-to-Service Communication with OpenFeign:** Simplifies HTTP client calls between microservices.
- **API Gateway with Spring Cloud Reactive Gateway:** A non-blocking, reactive gateway that handles routing.
- **Service Discovery with Eureka:** Ensures dynamic service registration and discovery.
- **Spring Data JPA:** Used for database interactions with `quizdb` and `questiondb`.
  
---

## Architecture Overview
The application consists of the following components:

### 1. **Question Service**
- **Purpose:** Manages the storage and retrieval of questions.
- **Technology Stack:** Spring Boot, Spring Data JPA, Eureka Client.
- **Database:** `questiondb`
- **Responsibilities:**
  - Stores questions along with their answers.
  - Provides an API to retrieve questions based on quiz IDs.
  
### 2. **Quiz Service**
- **Purpose:** Manages quizzes and connects to the Question Service for question retrieval.
- **Technology Stack:** Spring Boot, Spring Data JPA, Eureka Client, OpenFeign.
- **Database:** `quizdb`
- **Responsibilities:**
  - Stores quiz numbers and their corresponding question IDs.
  - Uses **OpenFeign** to call the `Question Service` for questions.

### 3. **Service Registry**
- **Purpose:** Handles service discovery, allowing services to register themselves and locate other services dynamically.
- **Technology Stack:** Spring Cloud Netflix Eureka.
- **Responsibilities:**
  - Registers all services and provides dynamic discovery.

### 4. **API Gateway**
- **Purpose:** Routes incoming requests to the appropriate microservices.
- **Technology Stack:** Spring Cloud Gateway (Reactive).
- **Responsibilities:**
  - Acts as a central entry point for the application.
  - Handles security, routing, and cross-cutting concerns.

---

## System Workflow
1. **Quiz Creation:** 
   - The user interacts with the `Quiz Service` to create a quiz.
   - The `Quiz Service` calls the `Question Service` via **OpenFeign** to fetch questions based on quiz IDs.
2. **Question Storage:** 
   - The `Question Service` stores all questions and their answers in `questiondb`.
3. **Quiz Storage:** 
   - The `Quiz Service` stores the quiz details (quiz ID and question IDs) in `quizdb`.
4. **API Gateway:** 
   - All user requests pass through the **Reactive API Gateway**, which routes them to the appropriate service.
5. **Service Registry:** 
   - Both services register themselves with the Eureka **Service Registry**, enabling seamless communication.

---

## Technologies Used
- **Backend Framework:** Spring Boot
- **Microservices Frameworks:**
  - Spring Cloud Netflix Eureka for service registry.
  - OpenFeign for service-to-service communication.
  - Spring Cloud Gateway (Reactive) for API routing.
- **Database Interaction:** Spring Data JPA
- **Databases:** MySQL

---

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/chandima2000/Quiz-App.git
cd Quiz-App
```
### 2. Set Up the Databases
### 3. Configure Application Properties
Update the application.properties in each service with your database credentials and ports.
### 4. Start the Services
Run each service in the following order:

1. Service Registry
2. Question Service
3. Quiz Service
4. API Gateway
5. Access the Application
Use the API Gateway as the central entry point for all requests.
   
---
## Example Request Flow
1. Create a Quiz:
- Quiz Service receives a request to create a quiz.
- It uses OpenFeign to call Question Service to fetch questions by quiz ID.
2. Fetch Questions:
- Question Service retrieves the questions from questiondb and sends them back.
3. Store Quiz Data:
- Quiz Service stores the quiz and corresponding question IDs in quizdb.
  
---
## Future Enhancements
- Add user authentication using Spring Security.
- Deploy services using Docker and Kubernetes.
- Implement distributed tracing and logging for better monitoring.

---
## Acknowledgements
This project was created as part of a Spring Boot course, applying concepts of microservices, Spring Cloud, and RESTful APIs.

---
## Certificate
[udemy] (https://www.udemy.com/certificate/UC-cac964eb-b30c-4c99-b802-3fd9f489c44f)
