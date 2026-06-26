package com.awsfcaj.technical_support_backend.dto.response;

import com.awsfcaj.technical_support_backend.enums.Priority;
import com.awsfcaj.technical_support_backend.enums.TicketCategory;
import com.awsfcaj.technical_support_backend.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Condensed ticket response for list endpoints (avoids transferring full description/attachments).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketSummaryResponse {

    private String ticketId;
    private String title;
    private TicketCategory category;
    private Priority priority;
    private TicketStatus status;
    private String reporterName;
    private String assigneeName;
    private Instant createdAt;
    private Instant dueAt;
    private Boolean isOverdue;
    private Integer commentCount;
}
