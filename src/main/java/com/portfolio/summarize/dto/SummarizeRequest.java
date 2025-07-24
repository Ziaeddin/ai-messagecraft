package com.portfolio.summarize.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request for summarizing customer messages")
public record SummarizeRequest(
    @NotBlank(message = "Text to summarize cannot be blank")
    @Size(max = 10000, message = "Text cannot exceed 10000 characters")
    @Schema(description = "The text to be summarized", example = "Customer complained about delayed delivery, poor packaging, and damaged items. They are requesting a full refund and compensation for inconvenience...")
    String text,
    
    @Schema(description = "Maximum length of summary in tokens", example = "150")
    Integer maxTokens,
    
    @Schema(description = "Type of summary needed", example = "bullet_points")
    String summaryType
) {} 