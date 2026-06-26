package com.awsfcaj.technical_support_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Ticket statistics response for the dashboard/stats endpoint.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketStatsResponse {

    /** Total ticket count (all statuses). */
    private long totalTickets;

    /** Count of tickets that are currently overdue. */
    private long overdueTickets;

    /** Ticket count grouped by status (e.g. "OPEN" → 12). */
    private Map<String, Long> countByStatus;

    /** Ticket count grouped by priority (e.g. "CRITICAL" → 3). */
    private Map<String, Long> countByPriority;

    /** Ticket count grouped by category. */
    private Map<String, Long> countByCategory;

    /** Average time (in hours) from creation to resolution. */
    private Double averageResolutionHours;
}
