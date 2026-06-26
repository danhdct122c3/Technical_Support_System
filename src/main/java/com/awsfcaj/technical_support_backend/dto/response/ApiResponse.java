package com.awsfcaj.technical_support_backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Generic wrapper for all successful API responses.
 *
 * <pre>
 * {
 *   "success": true,
 *   "message": "Ticket created successfully",
 *   "traceId": "abc-123",
 *   "timestamp": "2025-01-01T00:00:00Z",
 *   "data": { ... }
 * }
 * </pre>
 *
 * @param <T> the payload type
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @Builder.Default
    private boolean success = true;

    private String message;
    private String traceId;
    private Instant timestamp;
    private T data;

    /** Convenience factory for a successful response with data. */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    /** Convenience factory for a successful response with data and a message. */
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    /** Convenience factory for a successful response with only a message (no data). */
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .timestamp(Instant.now())
                .build();
    }
}
