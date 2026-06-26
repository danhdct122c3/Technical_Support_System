package com.awsfcaj.technical_support_backend.entity;

import com.awsfcaj.technical_support_backend.enums.Priority;
import com.awsfcaj.technical_support_backend.enums.TicketCategory;
import com.awsfcaj.technical_support_backend.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.Instant;
import java.util.List;

/**
 * DynamoDB entity representing a support ticket.
 *
 * Table: tbs-tickets
 * PK: ticketId (String, UUID)
 *
 * GSI:
 *   - StatusIndex:     status (PK), createdAt (SK)
 *   - PriorityIndex:   priority (PK), createdAt (SK)
 *   - AssigneeIndex:   assigneeId (PK), createdAt (SK)
 *   - ReporterIndex:   reporterId (PK), createdAt (SK)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class Ticket {

    // ===== Primary Key =====

    private String ticketId;

    // ===== Core Fields =====

    private String title;
    private String description;
    private String category;       // TicketCategory enum value
    private String priority;       // Priority enum value
    private String status;         // TicketStatus enum value

    // ===== Reporter Info =====

    private String reporterId;
    private String reporterName;
    private String reporterEmail;

    // ===== Assignee Info =====

    private String assigneeId;
    private String assigneeName;
    private String assigneeDepartment;

    // ===== Timestamps =====

    private Instant createdAt;
    private Instant updatedAt;
    private Instant resolvedAt;
    private Instant dueAt;

    // ===== Computed / Tracking =====

    private Boolean isOverdue;
    private Boolean isDeleted;
    private Integer commentCount;

    // ===== Lists =====

    /** S3 pre-signed URLs or keys for attachments. */
    private List<String> attachments;

    /** Free-form tags for categorisation/search. */
    private List<String> tags;

    // ===== Notes =====

    /** Resolution summary written by agent when closing. */
    private String resolutionNote;

    // ===== DynamoDB Annotations =====

    @DynamoDbPartitionKey
    @DynamoDbAttribute("ticketId")
    public String getTicketId() {
        return ticketId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "StatusIndex")
    @DynamoDbAttribute("status")
    public String getStatus() {
        return status;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "PriorityIndex")
    @DynamoDbAttribute("priority")
    public String getPriority() {
        return priority;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "AssigneeIndex")
    @DynamoDbAttribute("assigneeId")
    public String getAssigneeId() {
        return assigneeId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "ReporterIndex")
    @DynamoDbAttribute("reporterId")
    public String getReporterId() {
        return reporterId;
    }

    @DynamoDbSecondarySortKey(indexNames = {"StatusIndex", "PriorityIndex", "AssigneeIndex", "ReporterIndex"})
    @DynamoDbAttribute("createdAt")
    public Instant getCreatedAt() {
        return createdAt;
    }
}
