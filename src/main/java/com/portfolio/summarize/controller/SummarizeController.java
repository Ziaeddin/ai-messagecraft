package com.portfolio.summarize.controller;

import com.portfolio.summarize.dto.ApiResponse;
import com.portfolio.summarize.dto.SummarizeRequest;
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
@RequestMapping("/api/v1/summarize")
@Tag(name = "Text Summarization", description = "AI-powered text summarization")
@CrossOrigin(origins = "*") // For demo purposes - restrict in production
public class SummarizeController {
    
    private static final Logger logger = LoggerFactory.getLogger(SummarizeController.class);
    
    private final OpenAiService openAiService;
    
    public SummarizeController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }
    
    @PostMapping("")
    @Operation(summary = "Summarize text", description = "Summarize customer messages or feedback using AI")
    public Mono<ResponseEntity<ApiResponse<String>>> summarizeText(
            @Valid @RequestBody SummarizeRequest request) {
        
        logger.info("Received summarization request for text length: {} characters", request.text().length());
        
        return openAiService.summarizeText(
                request.text(), 
                request.maxTokens(), 
                request.summaryType()
        )
        .map(summary -> ResponseEntity.ok(
            ApiResponse.success(summary, "Text summarized successfully")
        ))
        .onErrorReturn(ResponseEntity.status(500).body(
            ApiResponse.error("Failed to summarize text")
        ));
    }
    
    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "Summarize text with streaming", description = "Summarize customer messages or feedback using AI with real-time streaming")
    public Flux<String> summarizeTextStream(
            @Valid @RequestBody SummarizeRequest request) {
        
        logger.info("Received streaming summarization request for text length: {} characters", request.text().length());
        
        return openAiService.summarizeTextStream(
                request.text(), 
                request.maxTokens(), 
                request.summaryType()
        )
        .map(content -> "data: " + content + "\n\n")
        .onErrorReturn("data: Error occurred during summarization\n\n");
    }
    
    @GetMapping("/types")
    @Operation(summary = "Get summary types", description = "Get available summary types and their descriptions")
    public ResponseEntity<ApiResponse<Object>> getSummaryTypes() {
        var summaryTypes = java.util.Map.of(
            "brief", "A concise paragraph summary",
            "bullet_points", "Key points in bullet format",
            "detailed", "Comprehensive analysis with details",
            "key_points", "Main highlights and takeaways"
        );
        
        return ResponseEntity.ok(ApiResponse.success(summaryTypes, "Summary types retrieved successfully"));
    }
} 