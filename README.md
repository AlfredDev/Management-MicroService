# Nutri-Management [Microservices | Spring Boot 3 | Spring Cloud]

## Overview

Nutri-Management is a scalable microservices-based system designed to manage nutrition plans and patient appointments, along with payment and notification services. It leverages Spring Boot 3, Spring Cloud, Kafka, and other modern technologies to provide a resilient, distributed, and highly available solution.

The system handles the following core functionalities:

- User and patient management
- Appointment scheduling
- Payment processing
- Asynchronous communication for notifications
- Distributed tracing using Zipkin
- Centralized configuration management with Config Server
- Service discovery via Eureka

## Architecture

### Components

- **API Gateway (API GW)**: Acts as the single entry point for client requests. It routes traffic to various microservices such as User, Patient, and Appointment services.
- **User Service**: Manages user information and roles within the system.
- **Patient Service**: Handles patient-related data, such as profiles and medical history.
- **Appointment Service**: Manages appointments, including scheduling and retrieval of appointments from MongoDB.
- **Payment Service**: Processes payments and sends confirmation messages asynchronously via the message broker (Kafka).
- **Notification Service**: Listens for events from Kafka and sends notifications (e.g., appointment or payment confirmations) to users. Data is persisted in MongoDB.
- **Message Broker (Kafka)**: Facilitates asynchronous communication between services, especially for sending payment and appointment confirmations.
- **Zipkin**: Provides distributed tracing to monitor the flow of requests across services.
- **Eureka Server**: Service discovery component that registers all microservices, enabling them to find and communicate with each other dynamically.
- **Config Server**: Centralized management of configuration properties for all services in the system.

### Flow

1. **User & Patient Management**:
    - Clients send requests via the API Gateway to `/users` or `/patients`, which are handled by the **User** and **Patient** microservices.

2. **Appointment Scheduling**:
    - The **Appointment** service manages requests sent to `/appointments`. Appointments are stored and retrieved from MongoDB.

3. **Payment Processing**:
    - The **Payment** service handles payment transactions and sends confirmation messages asynchronously to the **Notification** service via the **Message Broker** (Kafka).

4. **Notifications**:
    - The **Notification** service listens to the **Message Broker** for appointment or payment events. Notifications are sent to users and stored in MongoDB.

5. **Distributed Tracing**:
    - Every request is traced using **Zipkin** to allow monitoring of request propagation across services for improved observability.

6. **Configuration & Discovery**:
    - **Config Server** manages application configuration for all microservices.
    - **Eureka Server** is responsible for service registration and discovery, enabling each microservice to find others within the private network.

![Arquitectura del Proyecto](./diagrams/Nutrioologo-Global-Architecture.drawio.svg)

## Technologies

- **Spring Boot 3**: For developing individual microservices.
- **Spring Cloud**: For managing service discovery, configuration, and communication between microservices.
- **Kafka**: Message broker used for asynchronous communication between services.
- **MongoDB**: NoSQL database used by Appointment and Notification services for persistence.
- **Zipkin**: For distributed tracing and monitoring.
- **Eureka**: Service registry to allow microservices to discover and communicate with each other.
- **Config Server**: Centralized configuration management for all microservices.

## Prerequisites

- **Java 17+**
- **Maven 3+**
- **Docker & Docker Compose**
- **Kafka** (locally or through a Docker setup)
- **MongoDB** (locally or through a Docker setup)

## Running the Application

### With Docker

A `docker-compose.yml` file is provided to orchestrate the startup of all microservices along with supporting infrastructure like Kafka, MongoDB, Zipkin, and Eureka.

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/nutri-management.git
   ```

2. Navigate to the project root and run:
   ```bash
   docker-compose up --build
   ```

3. Access the following services:

    - **API Gateway**: `http://localhost:8080`
    - **Eureka Server**: `http://localhost:8761`
    - **Zipkin**: `http://localhost:9411`
    - **Kafka**: Configured for messaging (no direct UI).

### Running Without Docker

1. Start each microservice manually by navigating to each service directory and using Maven:
   ```bash
   mvn spring-boot:run
   ```

2. Make sure to run Kafka and MongoDB instances if you are not using Docker.

## Endpoints

- `/users`: User management (create, update, delete users).
- `/patients`: Patient management (view, add, edit patient profiles).
- `/appointments`: Appointment management (schedule, cancel appointments).
- `/payments`: Payment processing.

## Distributed Tracing

The system uses **Zipkin** to trace requests as they flow across services. You can access the Zipkin dashboard at `http://localhost:9411` to visualize traces, latency, and dependencies.

## Service Discovery

The **Eureka Server** allows services to register and discover each other. Visit `http://localhost:8761` to view registered services and their statuses.

## Future Improvements

- Implement authorization and authentication mechanisms (e.g., OAuth2, JWT).
- Add a front-end application for user interaction.
- Extend the microservices to handle more complex nutrition management features like diet tracking and reports.

---

This README provides a detailed overview of the system, instructions for running it, and descriptions of each service within the microservices architecture. Let me know if you need any more customization!
