
---
# Health Management Microservices
## Overview
This project is a health management system built using **Spring Boot** and **Spring Cloud** to follow the microservices architecture. It is designed to handle various healthcare processes, such as managing patients, appointments, tracking patient records, handling prescriptions (recipes), and making payments. The system also supports event-driven communication using **Kafka** for asynchronous messaging and **Zipkin** for distributed tracing.

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


## Technologies

- **Spring Boot 3**: For developing individual microservices.
- **Spring Cloud**: For managing service discovery, configuration, and communication between microservices.
- **Kafka**: Message broker used for asynchronous communication between services.
- **MongoDB**: NoSQL database used by Appointment Recipe and Notification services for persistence.
- **Zipkin**: For distributed tracing and monitoring.
- **Eureka**: Service registry to allow microservices to discover and communicate with each other.
- **Config Server**: Centralized configuration management for all microservices.
- **Java 17**
- **Maven**
- **MySQL** (User and Patient data)
- **Postgres** (Appointment data)
- **Docker** (For containerized services)

### Architecture Diagram
![Architecture Diagram](diagrams/Nutrioologo-Global-Architecture.drawio.svg)

### Flow of Communication

1. **User and Patient Management**: Users access the system via the API Gateway, which routes requests to the **User** and **Patient** services. Each user manages their own set of patients.

2. **Appointments**: The **Appointment Service** manages patient appointments. When an appointment is created or completed, an event is published to the **Message Broker** (Kafka), ensuring that the **Tracking** and **Recipe Services** are notified.

3. **Tracking**: The **Tracking Service** listens for appointment-related events and updates patient activity records accordingly. It can also be accessed directly via the `/tracking/patients/{id}` endpoint to view or update patient tracking data.

4. **Recipe Management**: The **Recipe Service** handles medical prescriptions or dietary recommendations for patients. It also listens for events from the **Appointment Service** to generate or update prescriptions after an appointment.

5. **Payment**: The **Payment Service** manages payments for appointments. Once a payment is confirmed, an event is sent to the **Message Broker** for further processing (e.g., notifications).

6. **Notifications**: The **Notification Service** listens to events from both the **Appointment** and **Payment Services** to notify patients and doctors about their appointments, prescriptions, or payments.

7. **Monitoring**: **Zipkin** is used for distributed tracing across all microservices, providing detailed insights into the request flow across services and helping identify performance bottlenecks.

### How to Run the Project

#### 1. Clone the repository
```bash
git clone https://github.com/AlfredDev/Management-MicroService.git
cd your-repository
```

#### 2. Build the services
```bash
mvn clean install
```

#### 3. Run the microservices
Each microservice has its own module within the project. You can navigate to each service's directory and run it using Maven, or run all services simultaneously using Docker.

Example for running a service:

```bash
cd user-service
mvn spring-boot:run
```

#### 4. Accessing the Application
- **API Gateway**: `http://localhost:8080`
- **Zipkin**: `http://localhost:9411`
- **Kafka**: `localhost:9092` (for message broker communication)

## API Endpoints

Here are some key API endpoints exposed via the API Gateway:

- **User**: `GET /users/{id}`
- **Patient**: `GET /patients/{id}`
- **Tracking**: `GET /tracking/patients/{id}`
- **Recipe**: `GET /recipes/patients/{id}`
- **Appointment**: `POST /appointments`
- **Payment**: `POST /payments`

Refer to the Swagger documentation for full API details.

## Event-Driven Communication
The project utilizes **Kafka** to enable asynchronous messaging between services. Key events include:
- **Appointment Service**: Sends appointment creation and completion events.
- **Payment Service**: Sends payment confirmation events.
- **Tracking & Recipe Services**: Listen for appointment events to update medical records and prescriptions.

## Monitoring & Distributed Tracing
The project integrates with **Zipkin** to provide distributed tracing and performance monitoring. Each service sends tracing data to the Zipkin server to allow detailed insight into request flows and bottlenecks.

## Further Enhancements
- Adding **Resilience4j** or **Hystrix** for fault tolerance.
- Implementing **OAuth2** for securing API requests.

## License
This project is licensed under the MIT License.
---