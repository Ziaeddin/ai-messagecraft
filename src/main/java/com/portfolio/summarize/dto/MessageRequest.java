package com.portfolio.summarize.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request for generating a customer message")
public record MessageRequest(
    @NotBlank(message = "Prompt cannot be blank")
    @Size(max = 2000, message = "Prompt cannot exceed 2000 characters")
    @Schema(description = "The prompt for message generation", example = "Generate a professional response to a customer complaint about delayed shipping")
    String prompt,
    
    @Schema(description = "Maximum number of tokens in response", example = "500")
    Integer maxTokens,
    
    @Schema(description = "Temperature for randomness (0.0 to 1.0)", example = "0.7")
    Double temperature,
    
    @Schema(description = "Type of message to generate", example = "customer_response")
    String messageType
) {} 