package com.portfolio.summarize.service;

import com.portfolio.summarize.config.OpenAiProperties;
import com.portfolio.summarize.model.OpenAiRequest;
import com.portfolio.summarize.model.OpenAiResponse;
import com.portfolio.summarize.model.StreamResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OpenAiService {
    
    private static final Logger logger = LoggerFactory.getLogger(OpenAiService.class);
    
    private final WebClient webClient;
    private final OpenAiProperties properties;
    
    public OpenAiService(WebClient openAiWebClient, OpenAiProperties properties) {
        this.webClient = openAiWebClient;
        this.properties = properties;
    }
    
    /**
     * Generate a message using OpenAI GPT-4 with non-streaming response
     */
    public Mono<String> generateMessage(String prompt, Integer maxTokens, Double temperature) {
        logger.info("Generating message for prompt: {}", prompt.substring(0, Math.min(prompt.length(), 50)) + "...");
        
        OpenAiRequest request = createRequest(prompt, maxTokens, temperature, false);
        
        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(OpenAiResponse.class)
                .map(response -> {
                    if (response.choices() != null && !response.choices().isEmpty()) {
                        return response.choices().get(0).message().content();
                    }
                    throw new RuntimeException("No response content from OpenAI");
                })
                .doOnSuccess(result -> logger.info("Message generated successfully"))
                .doOnError(error -> logger.error("Error generating message: {}", error.getMessage()));
    }
    
    /**
     * Generate a message using OpenAI GPT-4 with streaming response
     */
    public Flux<String> generateMessageStream(String prompt, Integer maxTokens, Double temperature) {
        logger.info("Generating streaming message for prompt: {}", prompt.substring(0, Math.min(prompt.length(), 50)) + "...");
        
        OpenAiRequest request = createRequest(prompt, maxTokens, temperature, true);
        
        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(request)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class)
                .filter(chunk -> !chunk.trim().isEmpty() && !chunk.equals("data: [DONE]"))
                .map(chunk -> chunk.replace("data: ", ""))
                .filter(chunk -> !chunk.trim().isEmpty())
                .flatMap(this::parseStreamChunk)
                .doOnComplete(() -> logger.info("Streaming message generation completed"))
                .doOnError(error -> logger.error("Error in streaming message generation: {}", error.getMessage()));
    }
    
    /**
     * Summarize text using OpenAI GPT-4
     */
    public Mono<String> summarizeText(String text, Integer maxTokens, String summaryType) {
        logger.info("Summarizing text of length: {}", text.length());
        
        String prompt = createSummaryPrompt(text, summaryType);
        return generateMessage(prompt, maxTokens, 0.3); // Lower temperature for more consistent summaries
    }
    
    /**
     * Summarize text using OpenAI GPT-4 with streaming
     */
    public Flux<String> summarizeTextStream(String text, Integer maxTokens, String summaryType) {
        logger.info("Summarizing text with streaming, length: {}", text.length());
        
        String prompt = createSummaryPrompt(text, summaryType);
        return generateMessageStream(prompt, maxTokens, 0.3);
    }
    
    private OpenAiRequest createRequest(String prompt, Integer maxTokens, Double temperature, boolean stream) {
        List<OpenAiRequest.Message> messages = List.of(
                new OpenAiRequest.Message("user", prompt)
        );
        
        return new OpenAiRequest(
                properties.model(),
                messages,
                maxTokens != null ? maxTokens : properties.maxTokens(),
                temperature != null ? temperature : properties.temperature(),
                stream
        );
    }
    
    private String createSummaryPrompt(String text, String summaryType) {
        String basePrompt = "Please summarize the following text";
        
        if (summaryType != null) {
            basePrompt += switch (summaryType.toLowerCase()) {
                case "bullet_points" -> " in bullet points";
                case "brief" -> " in a brief paragraph";
                case "detailed" -> " with detailed analysis";
                case "key_points" -> " highlighting key points";
                default -> "";
            };
        }
        
        return basePrompt + ":\n\n" + text;
    }
    
    private Flux<String> parseStreamChunk(String chunk) {
        try {
            // Parse the streaming response chunk
            // This is a simplified parser - in production, you'd want more robust JSON parsing
            if (chunk.contains("\"content\":")) {
                String content = extractContentFromChunk(chunk);
                if (content != null && !content.isEmpty()) {
                    return Flux.just(content);
                }
            }
            return Flux.empty();
        } catch (Exception e) {
            logger.warn("Error parsing stream chunk: {}", e.getMessage());
            return Flux.empty();
        }
    }
    
    private String extractContentFromChunk(String chunk) {
        try {
            // Simple content extraction - in production use proper JSON parsing
            int startIndex = chunk.indexOf("\"content\":\"") + 11;
            int endIndex = chunk.indexOf("\"", startIndex);
            if (startIndex > 10 && endIndex > startIndex) {
                return chunk.substring(startIndex, endIndex)
                        .replace("\\n", "\n")
                        .replace("\\\"", "\"");
            }
        } catch (Exception e) {
            logger.debug("Could not extract content from chunk: {}", chunk);
        }
        return null;
    }
} 