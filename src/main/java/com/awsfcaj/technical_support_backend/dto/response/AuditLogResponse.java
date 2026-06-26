package com.awsfcaj.technical_support_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Audit log entry response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogResponse {

    private String logId;
    private String ticketId;
    private String action;
    private String actorId;
    private String actorName;
    private String oldValue;
    private String newValue;
    private String note;
    private Instant changedAt;
}
