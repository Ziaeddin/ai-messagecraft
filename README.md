# AI-MessageCraft 🤖✨

**Professional AI-Powered Message Generation & Summarization Service**

A modern Spring Boot microservice showcasing enterprise-grade OpenAI GPT-4 integration for generating professional customer messages and intelligent text summarization with real-time streaming capabilities.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-brightgreen?style=flat-square&logo=spring)
![OpenAI](https://img.shields.io/badge/OpenAI-GPT--4-black?style=flat-square&logo=openai)
![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=flat-square&logo=docker)
![License](https://img.shields.io/badge/License-Portfolio-purple?style=flat-square)

---

## 🏗️ System Architecture

```mermaid
graph TB
    subgraph "Client Applications"
        WEB[Web App]
        MOBILE[Mobile App]
        API_CLIENT[API Client]
    end
    
    subgraph "AI-MessageCraft Service"
        subgraph "Controllers"
            MSG_CTRL[Message Controller]
            SUM_CTRL[Summarize Controller]
        end
        
        subgraph "Service Layer"
            AI_SERVICE[OpenAI Service]
            STREAM_SERVICE[Streaming Service]
        end
        
        subgraph "Configuration"
            CONFIG[OpenAI Config]
            PROPS[Properties]
        end
    end
    
    subgraph "External Services"
        OPENAI[OpenAI GPT-4 API]
    end
    
    subgraph "Infrastructure"
        DOCKER[Docker Container]
        HEALTH[Health Monitoring]
        SWAGGER[API Documentation]
    end
    
    WEB --> MSG_CTRL
    MOBILE --> SUM_CTRL
    API_CLIENT --> MSG_CTRL
    
    MSG_CTRL --> AI_SERVICE
    SUM_CTRL --> AI_SERVICE
    
    AI_SERVICE --> STREAM_SERVICE
    AI_SERVICE --> CONFIG
    CONFIG --> PROPS
    
    AI_SERVICE --> OPENAI
    
    MSG_CTRL --> SWAGGER
    SUM_CTRL --> SWAGGER
    
    AI_SERVICE --> HEALTH
    HEALTH --> DOCKER
    
    style OPENAI fill:#ff9999
    style AI_SERVICE fill:#99ccff
    style DOCKER fill:#99ff99
    style SWAGGER fill:#ffcc99
```

## 🛠 Technology Stack & Features

```mermaid
mindmap
  root((AI-MessageCraft))
    Backend Framework
      Spring Boot 3.2.0
        WebFlux Reactive
        Spring Actuator
        Configuration Properties
      Java 17
        Modern Language Features
        Record Classes
        Switch Expressions
    AI Integration
      OpenAI GPT-4
        Chat Completions API
        Streaming Responses
        Custom Parameters
      Reactive WebClient
        Non-blocking I/O
        Error Handling
        Circuit Breaker Ready
    API Design
      RESTful Architecture
        JSON Request/Response
        Server-Sent Events
        HTTP Status Codes
      Swagger/OpenAPI 3
        Interactive Documentation
        Try-it-out Features
        Schema Validation
    DevOps & Deployment
      Docker
        Multi-stage Build
        Optimized Images
        Health Checks
      Maven
        Dependency Management
        Build Automation
        Testing Integration
    Quality & Testing
      JUnit 5
        Unit Tests
        Integration Tests
        Mockito
      Validation
        Input Validation
        Error Handling
        Global Exception Handler
```

---

## 🚀 Features

- **AI-Powered Message Generation**: Generate professional customer service messages using GPT-4
- **Intelligent Text Summarization**: Summarize customer feedback and messages in various formats
- **Real-time Streaming**: Stream responses in real-time for better user experience
- **RESTful API**: Clean, well-documented REST endpoints
- **Input Validation**: Comprehensive request validation with detailed error messages
- **Error Handling**: Robust error handling with meaningful error responses
- **Swagger Documentation**: Interactive API documentation with Swagger UI
- **Health Monitoring**: Built-in health checks and monitoring endpoints
- **Configurable**: Environment-based configuration for different deployment scenarios

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- OpenAI API Key

## 🔧 Setup & Installation

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/ai-messagecraft.git
cd ai-messagecraft
```

### 2. Configure Environment Variables
Create a `.env` file or set environment variables:

```bash
export OPENAI_API_KEY=your-openai-api-key-here
export OPENAI_MODEL=gpt-4  # Optional, defaults to gpt-4
export OPENAI_MAX_TOKENS=1000  # Optional, defaults to 1000
export OPENAI_TEMPERATURE=0.7  # Optional, defaults to 0.7
```

### 3. Build the Application
```bash
mvn clean compile
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 🎬 Live Demo

![Demo](assets/demo-banner.md)

### Quick Start Demo
```bash
# Test the API instantly
curl -X POST http://localhost:8080/api/v1/messages/generate \
  -H "Content-Type: application/json" \
  -d '{"prompt": "Create a professional thank you message"}'
```

### Real-time Streaming
```bash
# Watch AI generate content in real-time
curl -N -X POST http://localhost:8080/api/v1/messages/generate/stream \
  -H "Accept: text/event-stream" \
  -d '{"prompt": "Write a welcome message for new customers"}'
```

## 🔄 Message Flow & Streaming

```mermaid
sequenceDiagram
    participant Client
    participant Controller
    participant Service
    participant OpenAI
    
    Note over Client,OpenAI: Message Generation Flow
    
    Client->>Controller: POST /api/v1/messages/generate
    Note right of Client: {<br/>"prompt": "Generate apology message",<br/>"maxTokens": 500,<br/>"temperature": 0.7<br/>}
    
    Controller->>Controller: Validate Request
    Controller->>Service: generateMessage()
    
    Service->>Service: Create OpenAI Request
    Service->>OpenAI: POST /chat/completions
    Note right of Service: {<br/>"model": "gpt-4",<br/>"messages": [...],<br/>"max_tokens": 500<br/>}
    
    OpenAI-->>Service: AI Generated Response
    Service-->>Controller: Generated Message
    Controller-->>Client: JSON Response
    Note left of Controller: {<br/>"status": "success",<br/>"data": "Generated message...",<br/>"timestamp": "2024-01-01T12:00:00"<br/>}
    
    Note over Client,OpenAI: Streaming Flow
    
    Client->>Controller: POST /api/v1/messages/generate/stream
    Controller->>Service: generateMessageStream()
    Service->>OpenAI: POST /chat/completions (stream=true)
    
    loop Real-time Streaming
        OpenAI-->>Service: Stream Chunk
        Service-->>Controller: Processed Chunk
        Controller-->>Client: Server-Sent Event
        Note left of Controller: data: Generated content chunk...
    end
```

## 🎯 Core Features Overview

```mermaid
graph LR
    subgraph "Core Features"
        A[AI Message Generation]
        B[Text Summarization]
        C[Real-time Streaming]
        D[Template Management]
    end
    
    subgraph "API Capabilities"
        E[RESTful Endpoints]
        F[Input Validation]
        G[Error Handling]
        H[Swagger Documentation]
    end
    
    subgraph "AI Integration"
        I[OpenAI GPT-4]
        J[Configurable Parameters]
        K[Multiple Summary Types]
        L[Custom Prompts]
    end
    
    subgraph "Production Features"
        M[Health Monitoring]
        N[Docker Support]
        O[Environment Config]
        P[Logging & Metrics]
    end
    
    A --> E
    B --> F
    C --> G
    D --> H
    
    E --> I
    F --> J
    G --> K
    H --> L
    
    I --> M
    J --> N
    K --> O
    L --> P
    
    style A fill:#ff6b6b
    style B fill:#4ecdc4
    style C fill:#45b7d1
    style D fill:#96ceb4
    style I fill:#feca57
    style M fill:#ff9ff3
```

## 📖 API Documentation

Once the application is running, you can access:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **Health Check**: http://localhost:8080/actuator/health

### 📚 Additional Documentation
- [🏗️ Architecture Overview](docs/ARCHITECTURE.md)
- [📋 Complete API Examples](docs/API_EXAMPLES.md)
- [🎯 Live Demo Guide](assets/demo-banner.md)

## 🔌 API Endpoints Overview

```mermaid
graph TD
    subgraph "Message Generation APIs"
        A[POST /api/v1/messages/generate]
        B[POST /api/v1/messages/generate/stream]
        C[GET /api/v1/messages/templates]
    end
    
    subgraph "Text Summarization APIs"
        D[POST /api/v1/summarize]
        E[POST /api/v1/summarize/stream]
        F[GET /api/v1/summarize/types]
    end
    
    subgraph "System APIs"
        G[GET /actuator/health]
        H[GET /actuator/info]
        I[GET /swagger-ui.html]
        J[GET /api-docs]
    end
    
    subgraph "Request Types"
        K[JSON Request Body]
        L[Server-Sent Events]
        M[JSON Response]
    end
    
    A --> K
    A --> M
    B --> K
    B --> L
    D --> K
    D --> M
    E --> K
    E --> L
    
    style A fill:#e3f2fd
    style B fill:#e8f5e9
    style D fill:#fff3e0
    style E fill:#fce4ec
    style G fill:#f3e5f5
```

### Message Generation

#### Generate Message (Non-streaming)
```http
POST /api/v1/messages/generate
Content-Type: application/json

{
  "prompt": "Generate a professional apology for delayed shipping",
  "maxTokens": 500,
  "temperature": 0.7,
  "messageType": "apology"
}
```

#### Generate Message (Streaming)
```http
POST /api/v1/messages/generate/stream
Content-Type: application/json
Accept: text/event-stream

{
  "prompt": "Generate a professional welcome message for new customers",
  "maxTokens": 300,
  "temperature": 0.8,
  "messageType": "welcome"
}
```

#### Get Message Templates
```http
GET /api/v1/messages/templates
```

### Text Summarization

#### Summarize Text (Non-streaming)
```http
POST /api/v1/summarize
Content-Type: application/json

{
  "text": "Long customer feedback text to be summarized...",
  "maxTokens": 150,
  "summaryType": "bullet_points"
}
```

#### Summarize Text (Streaming)
```http
POST /api/v1/summarize/stream
Content-Type: application/json
Accept: text/event-stream

{
  "text": "Customer feedback text...",
  "maxTokens": 200,
  "summaryType": "brief"
}
```

#### Get Summary Types
```http
GET /api/v1/summarize/types
```

## 📊 Response Format

### Standard API Response
```json
{
  "status": "success",
  "message": "Message generated successfully",
  "data": "Generated message content here...",
  "error": null,
  "timestamp": "2024-01-01T12:00:00"
}
```

### Error Response
```json
{
  "status": "error",
  "message": "Validation failed",
  "data": null,
  "error": "Invalid input parameters",
  "timestamp": "2024-01-01T12:00:00"
}
```

### Streaming Response
```
data: Generated content chunk 1

data: Generated content chunk 2

data: Generated content chunk 3

```

## 🎯 Use Cases

### Customer Service
- Generate professional responses to customer complaints
- Create welcome messages for new customers
- Draft follow-up communications
- Produce resolution confirmations

### Content Management
- Summarize customer feedback for analysis
- Create executive summaries of support tickets
- Generate bullet-point summaries for reporting
- Extract key points from lengthy communications

## 🚀 Deployment & DevOps Pipeline

```mermaid
graph TB
    subgraph "Development"
        CODE[Source Code]
        TEST[Unit Tests]
        BUILD[Maven Build]
    end
    
    subgraph "Containerization"
        DOCKER_BUILD[Docker Build]
        IMAGE[Docker Image]
        COMPOSE[Docker Compose]
    end
    
    subgraph "Deployment"
        LOCAL[Local Development]
        STAGING[Staging Environment]
        PROD[Production]
    end
    
    subgraph "Monitoring"
        HEALTH[Health Checks]
        METRICS[Metrics Collection]
        LOGS[Centralized Logging]
    end
    
    CODE --> TEST
    TEST --> BUILD
    BUILD --> DOCKER_BUILD
    DOCKER_BUILD --> IMAGE
    IMAGE --> COMPOSE
    
    COMPOSE --> LOCAL
    COMPOSE --> STAGING
    COMPOSE --> PROD
    
    LOCAL --> HEALTH
    STAGING --> METRICS
    PROD --> LOGS
    
    HEALTH --> METRICS
    METRICS --> LOGS
    
    style CODE fill:#e1f5fe
    style DOCKER_BUILD fill:#f3e5f5
    style PROD fill:#e8f5e8
    style HEALTH fill:#fff3e0
```

## 🏗 Clean Architecture Pattern

The application follows a clean architecture pattern:

```
src/main/java/com/portfolio/summarize/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── dto/            # Data Transfer Objects
├── exception/      # Exception handling
├── model/          # Domain models
└── service/        # Business logic
```

### Key Components

- **OpenAiService**: Core service for OpenAI API integration
- **MessageController**: Handles message generation endpoints
- **SummarizeController**: Handles text summarization endpoints
- **GlobalExceptionHandler**: Centralized error handling
- **OpenAiProperties**: Configuration properties management

## 🧪 Testing

Run tests with:
```bash
mvn test
```

## 🚀 Deployment

### Docker Deployment
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/summarize-srv-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Environment Variables for Production
```bash
OPENAI_API_KEY=your-production-api-key
OPENAI_MODEL=gpt-4
SPRING_PROFILES_ACTIVE=prod
```

### Quick Deployment with Docker Compose
```bash
# Copy environment template
cp .env.example .env
# Edit .env with your OpenAI API key
# Start the service
docker-compose up -d
```

## 📈 Monitoring & Health Checks

The application includes built-in monitoring:

- Health endpoint: `/actuator/health`
- Info endpoint: `/actuator/info`
- Metrics endpoint: `/actuator/prometheus` (if enabled)

## 🔒 Security Considerations

- API key is externalized via environment variables
- Input validation on all endpoints
- CORS configuration (adjust for production)
- Rate limiting should be implemented for production use

## 🤝 Contributing

This is a portfolio project demonstrating AI integration skills. For improvements or suggestions:

1. Fork the repository
2. Create a feature branch
3. Submit a pull request

## 📜 License

This project is created for portfolio demonstration purposes.

## 🎯 Portfolio Highlights

This project demonstrates:

- **Modern Spring Boot Development**: Using Spring Boot 3.x with Java 17
- **Reactive Programming**: WebFlux for streaming responses
- **AI Integration**: Professional OpenAI GPT-4 integration
- **API Design**: RESTful API design with proper documentation
- **Error Handling**: Comprehensive error handling and validation
- **Configuration Management**: Externalized configuration for different environments
- **Code Quality**: Clean, maintainable code with proper logging
- **Documentation**: Comprehensive API documentation with Swagger

---

**Built with ❤️ for demonstrating AI integration capabilities in enterprise applications.** 