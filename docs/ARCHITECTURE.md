# AI-MessageCraft Architecture

## System Overview

AI-MessageCraft is a Spring Boot microservice designed to showcase enterprise-grade AI integration capabilities. The service provides RESTful APIs for message generation and text summarization using OpenAI's GPT-4 model.

## Architecture Diagram

```mermaid
graph TB
    subgraph "Client Layer"
        WEB[Web Applications]
        MOBILE[Mobile Apps]
        CLI[CLI Tools]
    end
    
    subgraph "API Gateway"
        GATEWAY[Load Balancer/API Gateway]
    end
    
    subgraph "AI-MessageCraft Service"
        subgraph "Presentation Layer"
            MSG_CTRL[MessageController]
            SUM_CTRL[SummarizeController]
        end
        
        subgraph "Business Layer"
            AI_SVC[OpenAiService]
            STREAM[Streaming Handler]
        end
        
        subgraph "Configuration"
            CONFIG[OpenAI Configuration]
            PROPS[Application Properties]
        end
    end
    
    subgraph "External APIs"
        OPENAI[OpenAI GPT-4 API]
    end
    
    subgraph "Infrastructure"
        DOCKER[Docker Container]
        HEALTH[Health Checks]
        METRICS[Metrics & Monitoring]
    end
    
    WEB --> GATEWAY
    MOBILE --> GATEWAY
    CLI --> GATEWAY
    
    GATEWAY --> MSG_CTRL
    GATEWAY --> SUM_CTRL
    
    MSG_CTRL --> AI_SVC
    SUM_CTRL --> AI_SVC
    
    AI_SVC --> STREAM
    AI_SVC --> CONFIG
    CONFIG --> PROPS
    
    AI_SVC --> OPENAI
    
    AI_SVC --> HEALTH
    HEALTH --> METRICS
    METRICS --> DOCKER
```

## Component Details

### Presentation Layer
- **MessageController**: Handles message generation requests (streaming and non-streaming)
- **SummarizeController**: Manages text summarization operations
- **GlobalExceptionHandler**: Centralized error handling and response formatting

### Business Layer
- **OpenAiService**: Core service managing OpenAI API interactions
- **Streaming Handler**: Manages real-time response streaming using WebFlux

### Configuration Layer
- **OpenAiProperties**: Type-safe configuration properties
- **WebClientConfig**: HTTP client configuration for external API calls

## Data Flow

### Message Generation Flow
1. Client sends generation request to MessageController
2. Controller validates input and delegates to OpenAiService
3. Service constructs OpenAI API request with proper formatting
4. OpenAI processes request and returns generated content
5. Service processes response and returns to client

### Streaming Flow
1. Client establishes Server-Sent Events connection
2. Service initiates streaming request to OpenAI
3. Real-time chunks are processed and forwarded to client
4. Connection maintained until completion or client disconnect

## Technology Stack

- **Framework**: Spring Boot 3.2.0 with WebFlux
- **Language**: Java 17
- **Build Tool**: Maven
- **Documentation**: Swagger/OpenAPI 3
- **Containerization**: Docker
- **Monitoring**: Spring Actuator
- **Testing**: JUnit 5, Mockito

## Security Considerations

- API keys externalized via environment variables
- Input validation on all endpoints
- Rate limiting (recommended for production)
- CORS configuration for cross-origin requests
- Health checks for monitoring

## Scalability

- Stateless design enables horizontal scaling
- Reactive programming model for efficient resource utilization
- Docker containerization for consistent deployment
- Health checks for load balancer integration

## Error Handling

- Comprehensive exception handling with meaningful error messages
- Circuit breaker pattern (recommended for production)
- Graceful degradation when external services are unavailable
- Structured error responses with proper HTTP status codes 