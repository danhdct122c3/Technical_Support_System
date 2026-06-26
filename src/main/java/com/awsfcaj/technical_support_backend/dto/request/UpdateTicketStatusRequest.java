package com.awsfcaj.technical_support_backend.dto.request;

import com.awsfcaj.technical_support_backend.enums.TicketStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request body for updating only the status of a ticket.
 * An optional note can be provided (e.g. resolution summary).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketStatusRequest {

    @NotNull(message = "New status is required")
    private TicketStatus status;

    @Size(max = 2000, message = "Note must not exceed 2000 characters")
    private String note;

    /** ID of the actor performing the status change (for audit log). */
    @NotNull(message = "Actor ID is required")
    private String actorId;

    private String actorName;
}
