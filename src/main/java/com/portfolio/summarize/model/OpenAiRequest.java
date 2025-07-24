package com.portfolio.summarize.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OpenAiRequest(
    String model,
    List<Message> messages,
    @JsonProperty("max_tokens") Integer maxTokens,
    Double temperature,
    Boolean stream
) {
    public record Message(
        String role,
        String content
    ) {}
} 