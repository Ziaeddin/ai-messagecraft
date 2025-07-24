package com.portfolio.summarize.controller;

import com.portfolio.summarize.dto.ApiResponse;
import com.portfolio.summarize.dto.MessageRequest;
import com.portfolio.summarize.service.OpenAiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/messages")
@Tag(name = "Message Generation", description = "AI-powered customer message generation")
@CrossOrigin(origins = "*") // For demo purposes - restrict in production
public class MessageController {
    
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    
    private final OpenAiService openAiService;
    
    public MessageController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }
    
    @PostMapping("/generate")
    @Operation(summary = "Generate a customer message", description = "Generate a professional customer message using AI")
    public Mono<ResponseEntity<ApiResponse<String>>> generateMessage(
            @Valid @RequestBody MessageRequest request) {
        
        logger.info("Received message generation request for type: {}", request.messageType());
        
        return openAiService.generateMessage(
                request.prompt(), 
                request.maxTokens(), 
                request.temperature()
        )
        .map(content -> ResponseEntity.ok(
            ApiResponse.success(content, "Message generated successfully")
        ))
        .onErrorReturn(ResponseEntity.status(500).body(
            ApiResponse.error("Failed to generate message")
        ));
    }
    
    @PostMapping(value = "/generate/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "Generate a customer message with streaming", description = "Generate a professional customer message using AI with real-time streaming")
    public Flux<String> generateMessageStream(
            @Valid @RequestBody MessageRequest request) {
        
        logger.info("Received streaming message generation request for type: {}", request.messageType());
        
        return openAiService.generateMessageStream(
                request.prompt(), 
                request.maxTokens(), 
                request.temperature()
        )
        .map(content -> "data: " + content + "\n\n")
        .onErrorReturn("data: Error occurred during generation\n\n");
    }
    
    @GetMapping("/templates")
    @Operation(summary = "Get message templates", description = "Get predefined message templates for different scenarios")
    public ResponseEntity<ApiResponse<Object>> getMessageTemplates() {
        var templates = java.util.Map.of(
            "apology", "I sincerely apologize for the inconvenience you've experienced. We take all customer concerns seriously and want to make this right.",
            "follow_up", "Thank you for reaching out to us. We wanted to follow up on your recent inquiry and ensure you received the assistance you needed.",
            "welcome", "Welcome to our service! We're excited to have you as a customer and look forward to providing you with excellent service.",
            "resolution", "We're pleased to inform you that your issue has been resolved. Please let us know if you need any further assistance."
        );
        
        return ResponseEntity.ok(ApiResponse.success(templates, "Message templates retrieved successfully"));
    }
} 