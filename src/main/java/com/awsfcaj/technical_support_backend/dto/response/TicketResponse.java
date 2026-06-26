package com.awsfcaj.technical_support_backend.dto.response;

import com.awsfcaj.technical_support_backend.enums.Priority;
import com.awsfcaj.technical_support_backend.enums.TicketCategory;
import com.awsfcaj.technical_support_backend.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * Full ticket detail response (used for single-ticket GET).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {

    private String ticketId;
    private String title;
    private String description;
    private TicketCategory category;
    private Priority priority;
    private TicketStatus status;

    // Reporter
    private String reporterId;
    private String reporterName;
    private String reporterEmail;

    // Assignee
    private String assigneeId;
    private String assigneeName;
    private String assigneeDepartment;

    // Timestamps
    private Instant createdAt;
    private Instant updatedAt;
    private Instant resolvedAt;
    private Instant dueAt;

    // Flags
    private Boolean isOverdue;
    private Integer commentCount;

    // Lists
    private List<String> tags;
    private List<String> attachments;

    private String resolutionNote;
}
