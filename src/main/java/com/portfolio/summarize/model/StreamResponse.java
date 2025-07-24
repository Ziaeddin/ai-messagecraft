package com.portfolio.summarize.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StreamResponse(
    String id,
    String object,
    Long created,
    String model,
    List<StreamChoice> choices
) {
    public record StreamChoice(
        Integer index,
        StreamDelta delta,
        @JsonProperty("finish_reason") String finishReason
    ) {}
    
    public record StreamDelta(
        String role,
        String content
    ) {}
} 