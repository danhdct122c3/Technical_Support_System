package com.awsfcaj.technical_support_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.Instant;

/**
 * DynamoDB entity recording every significant change to a ticket.
 *
 * Table: tbs-audit-logs
 * PK: logId (String, UUID)
 *
 * GSI:
 *   - TicketAuditIndex: ticketId (PK), changedAt (SK)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class AuditLog {

    private String logId;
    private String ticketId;

    /** The field or action that changed (e.g. "STATUS", "PRIORITY", "ASSIGN"). */
    private String action;

    private String actorId;
    private String actorName;

    /** Serialized previous value (JSON or plain string). */
    private String oldValue;

    /** Serialized new value (JSON or plain string). */
    private String newValue;

    /** Optional human-readable note attached to this change. */
    private String note;

    private Instant changedAt;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("logId")
    public String getLogId() {
        return logId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "TicketAuditIndex")
    @DynamoDbAttribute("ticketId")
    public String getTicketId() {
        return ticketId;
    }

    @DynamoDbSecondarySortKey(indexNames = "TicketAuditIndex")
    @DynamoDbAttribute("changedAt")
    public Instant getChangedAt() {
        return changedAt;
    }
}
