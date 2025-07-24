package com.portfolio.summarize.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Standard API response wrapper")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
    @Schema(description = "Response status", example = "success")
    String status,
    
    @Schema(description = "Response message", example = "Message generated successfully")
    String message,
    
    @Schema(description = "Response data")
    T data,
    
    @Schema(description = "Error details if any")
    String error,
    
    @Schema(description = "Response timestamp")
    LocalDateTime timestamp
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", null, data, null, LocalDateTime.now());
    }
    
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>("success", message, data, null, LocalDateTime.now());
    }
    
    public static <T> ApiResponse<T> error(String error) {
        return new ApiResponse<>("error", null, null, error, LocalDateTime.now());
    }
    
    public static <T> ApiResponse<T> error(String error, String message) {
        return new ApiResponse<>("error", message, null, error, LocalDateTime.now());
    }
} 