# 🎬 Live Demo & Screenshots

## 🚀 Quick Demo

### Message Generation in Action
```bash
# Generate a professional customer service message
curl -X POST http://localhost:8080/api/v1/messages/generate \
  -H "Content-Type: application/json" \
  -d '{
    "prompt": "Create an apology for delayed shipping",
    "messageType": "apology"
  }'
```

**Result:** AI generates a professional, empathetic customer service message in seconds.

### Real-time Streaming
```bash
# Watch AI generate content in real-time
curl -X POST http://localhost:8080/api/v1/messages/generate/stream \
  -H "Accept: text/event-stream" \
  -d '{"prompt": "Write a welcome email for new users"}'
```

**Result:** See content appear word-by-word as GPT-4 generates it!

## 📊 API Documentation Preview

Access the interactive Swagger UI at: `http://localhost:8080/swagger-ui.html`

**Features showcased:**
- ✅ Complete API documentation
- ✅ Try-it-out functionality  
- ✅ Request/response examples
- ✅ Authentication setup
- ✅ Error code explanations

## 🎯 Use Case Examples

| Scenario | Endpoint | Output |
|----------|----------|---------|
| Customer Complaint Response | `/messages/generate` | Professional, empathetic reply |
| New User Welcome | `/messages/generate` | Engaging onboarding message |
| Feedback Summarization | `/summarize` | Bullet-point key insights |
| Long Report Summary | `/summarize/stream` | Real-time condensed version |

## 🔍 Code Quality Highlights

```java
@Service
public class OpenAiService {
    
    /**
     * Generate streaming responses with proper error handling
     */
    public Flux<String> generateMessageStream(String prompt, 
                                            Integer maxTokens, 
                                            Double temperature) {
        
        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(createRequest(prompt, maxTokens, temperature, true))
                .retrieve()
                .bodyToFlux(String.class)
                .flatMap(this::parseStreamChunk)
                .doOnError(error -> logger.error("Stream error: {}", error.getMessage()));
    }
}
```

**Showcases:**
- Modern Java 17 features
- Reactive programming with WebFlux
- Proper error handling
- Clean architecture
- Comprehensive logging

## 🏗️ Architecture Visualization

The service demonstrates enterprise-grade architecture:

- **Layered Architecture**: Clear separation of concerns
- **Reactive Streams**: Non-blocking I/O for better performance  
- **Configuration Management**: Environment-based settings
- **Error Handling**: Graceful failure management
- **Documentation**: Self-documenting APIs
- **Containerization**: Docker-ready deployment

## 🎨 Portfolio Value

This project showcases:

1. **AI Integration Expertise** - Professional OpenAI API usage
2. **Modern Spring Boot** - Latest frameworks and best practices
3. **Reactive Programming** - WebFlux for high-performance streaming
4. **API Design** - RESTful design with proper documentation
5. **DevOps Ready** - Docker, health checks, monitoring
6. **Production Quality** - Error handling, validation, logging 