package com.awsfcaj.technical_support_backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Standardised error response body returned for all 4xx and 5xx responses.
 *
 * <pre>
 * {
 *   "traceId": "abc-123",
 *   "status": 404,
 *   "code": "TICKET_NOT_FOUND",
 *   "message": "Ticket not found with id: xyz",
 *   "details": { "field": "error description" },
 *   "timestamp": "2025-01-01T00:00:00Z"
 * }
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String traceId;
    private int status;
    private String code;
    private String message;

    /** Field-level validation errors or any extra context. */
    private Object details;

    private Instant timestamp;
}
