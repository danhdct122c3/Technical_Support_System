package com.awsfcaj.technical_support_backend.exception;

import com.awsfcaj.technical_support_backend.dto.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Central exception handler for all REST controllers.
 * Maps exceptions to standardised {@link ErrorResponse} bodies.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ===== 400 =====

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "BAD_REQUEST", ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
        return buildResponse(HttpStatus.BAD_REQUEST, "VALIDATION_FAILED", "Request validation failed", fieldErrors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "CONSTRAINT_VIOLATION", ex.getMessage(), null);
    }

    // ===== 404 =====

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTicketNotFound(TicketNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "TICKET_NOT_FOUND", ex.getMessage(), null);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", ex.getMessage(), null);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommentNotFound(CommentNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "COMMENT_NOT_FOUND", ex.getMessage(), null);
    }

    // ===== 409 =====

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateResourceException ex) {
        return buildResponse(HttpStatus.CONFLICT, "DUPLICATE_RESOURCE", ex.getMessage(), null);
    }

    // ===== 422 =====

    @ExceptionHandler(InvalidStatusTransitionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTransition(InvalidStatusTransitionException ex) {
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, "INVALID_STATUS_TRANSITION", ex.getMessage(), null);
    }

    // ===== DynamoDB =====

    @ExceptionHandler(DynamoDbException.class)
    public ResponseEntity<ErrorResponse> handleDynamoDb(DynamoDbException ex) {
        log.error("DynamoDB error: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.SERVICE_UNAVAILABLE, "DATABASE_ERROR", "A database error occurred", null);
    }

    // ===== 500 =====

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Unhandled exception: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "An unexpected error occurred", null);
    }

    // ===== Helper =====

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String code,
                                                         String message, Object details) {
        ErrorResponse body = ErrorResponse.builder()
                .traceId(UUID.randomUUID().toString())
                .code(code)
                .message(message)
                .details(details)
                .timestamp(Instant.now())
                .status(status.value())
                .build();
        return ResponseEntity.status(status).body(body);
    }
}
