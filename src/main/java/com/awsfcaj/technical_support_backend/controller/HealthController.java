package com.awsfcaj.technical_support_backend.controller;

import com.awsfcaj.technical_support_backend.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

/**
 * Custom health check endpoint.
 * Note: Actuator health is also available at /actuator/health.
 */
@RestController
@RequestMapping("/api/v1/health")
@RequiredArgsConstructor
@Tag(name = "Health", description = "Application health endpoints")
public class HealthController {

    private final HealthEndpoint healthEndpoint;

    @GetMapping
    @Operation(summary = "Get application health status")
    public ResponseEntity<ApiResponse<Map<String, Object>>> health() {
        HealthComponent health = healthEndpoint.health();
        Map<String, Object> status = Map.of(
                "status", health.getStatus().getCode(),
                "timestamp", Instant.now(),
                "service", "technical-support-backend"
        );
        return ResponseEntity.ok(ApiResponse.success(status));
    }
}
