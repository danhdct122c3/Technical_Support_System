package com.awsfcaj.technical_support_backend.controller;

import com.awsfcaj.technical_support_backend.dto.request.AssignTicketRequest;
import com.awsfcaj.technical_support_backend.dto.request.CreateTicketRequest;
import com.awsfcaj.technical_support_backend.dto.request.TicketFilterRequest;
import com.awsfcaj.technical_support_backend.dto.request.UpdateTicketRequest;
import com.awsfcaj.technical_support_backend.dto.request.UpdateTicketStatusRequest;
import com.awsfcaj.technical_support_backend.dto.response.ApiResponse;
import com.awsfcaj.technical_support_backend.dto.response.AuditLogResponse;
import com.awsfcaj.technical_support_backend.dto.response.PagedResponse;
import com.awsfcaj.technical_support_backend.dto.response.TicketResponse;
import com.awsfcaj.technical_support_backend.dto.response.TicketStatsResponse;
import com.awsfcaj.technical_support_backend.dto.response.TicketSummaryResponse;
import com.awsfcaj.technical_support_backend.enums.Priority;
import com.awsfcaj.technical_support_backend.enums.SortDirection;
import com.awsfcaj.technical_support_backend.enums.TicketCategory;
import com.awsfcaj.technical_support_backend.enums.TicketStatus;
import com.awsfcaj.technical_support_backend.service.AuditService;
import com.awsfcaj.technical_support_backend.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for ticket management.
 * Base path: /api/v1/tickets
 */
@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
@Tag(name = "Tickets", description = "Support ticket management endpoints")
public class TicketController {

    private final TicketService ticketService;
    private final AuditService auditService;

    @PostMapping
    @Operation(summary = "Create a new support ticket")
    public ResponseEntity<ApiResponse<TicketResponse>> createTicket(
            @Valid @RequestBody CreateTicketRequest request) {
        TicketResponse ticket = ticketService.createTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Ticket created successfully", ticket));
    }

    @GetMapping
    @Operation(summary = "List tickets with optional filters and pagination")
    public ResponseEntity<ApiResponse<PagedResponse<TicketSummaryResponse>>> getTickets(
            @RequestParam(required = false) TicketStatus status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) TicketCategory category,
            @RequestParam(required = false) String assigneeId,
            @RequestParam(required = false) String reporterId,
            @RequestParam(required = false) Boolean overdueOnly,
            @RequestParam(required = false) String lastEvaluatedKey,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(required = false) SortDirection sortDirection) {

        TicketFilterRequest filter = TicketFilterRequest.builder()
                .status(status)
                .priority(priority)
                .category(category)
                .assigneeId(assigneeId)
                .reporterId(reporterId)
                .overdueOnly(overdueOnly)
                .lastEvaluatedKey(lastEvaluatedKey)
                .limit(limit)
                .sortDirection(sortDirection)
                .build();

        return ResponseEntity.ok(ApiResponse.success(ticketService.getTickets(filter)));
    }

    @GetMapping("/stats")
    @Operation(summary = "Get ticket statistics")
    public ResponseEntity<ApiResponse<TicketStatsResponse>> getStats() {
        return ResponseEntity.ok(ApiResponse.success(ticketService.getStats()));
    }

    @GetMapping("/{ticketId}")
    @Operation(summary = "Get ticket detail by ID")
    public ResponseEntity<ApiResponse<TicketResponse>> getTicketById(
            @PathVariable String ticketId) {
        return ResponseEntity.ok(ApiResponse.success(ticketService.getTicketById(ticketId)));
    }

    @PatchMapping("/{ticketId}")
    @Operation(summary = "Update ticket information")
    public ResponseEntity<ApiResponse<TicketResponse>> updateTicket(
            @PathVariable String ticketId,
            @Valid @RequestBody UpdateTicketRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Ticket updated", ticketService.updateTicket(ticketId, request)));
    }

    @PatchMapping("/{ticketId}/status")
    @Operation(summary = "Update ticket status")
    public ResponseEntity<ApiResponse<TicketResponse>> updateTicketStatus(
            @PathVariable String ticketId,
            @Valid @RequestBody UpdateTicketStatusRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Status updated", ticketService.updateTicketStatus(ticketId, request)));
    }

    @PatchMapping("/{ticketId}/assign")
    @Operation(summary = "Assign ticket to a support agent")
    public ResponseEntity<ApiResponse<TicketResponse>> assignTicket(
            @PathVariable String ticketId,
            @Valid @RequestBody AssignTicketRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Ticket assigned", ticketService.assignTicket(ticketId, request)));
    }

    @DeleteMapping("/{ticketId}")
    @Operation(summary = "Soft-delete a ticket")
    public ResponseEntity<ApiResponse<Void>> deleteTicket(@PathVariable String ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok(ApiResponse.success("Ticket deleted"));
    }

    @GetMapping("/{ticketId}/history")
    @Operation(summary = "Get audit history for a ticket")
    public ResponseEntity<ApiResponse<PagedResponse<AuditLogResponse>>> getTicketHistory(
            @PathVariable String ticketId,
            @RequestParam(required = false) String lastEvaluatedKey,
            @RequestParam(defaultValue = "20") int limit) {
        return ResponseEntity.ok(
                ApiResponse.success(auditService.getTicketHistory(ticketId, lastEvaluatedKey, limit)));
    }
}
