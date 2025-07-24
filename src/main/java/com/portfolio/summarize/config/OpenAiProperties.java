package com.portfolio.summarize.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "openai")
@Validated
public record OpenAiProperties(
    @NotBlank String apiKey,
    @NotBlank String baseUrl,
    @NotBlank String model,
    Integer maxTokens,
    Double temperature
) {
    public OpenAiProperties {
        if (baseUrl == null) {
            baseUrl = "https://api.openai.com/v1";
        }
        if (model == null) {
            model = "gpt-4";
        }
        if (maxTokens == null) {
            maxTokens = 1000;
        }
        if (temperature == null) {
            temperature = 0.7;
        }
    }
} 