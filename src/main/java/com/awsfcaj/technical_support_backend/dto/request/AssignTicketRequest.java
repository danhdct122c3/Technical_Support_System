package com.awsfcaj.technical_support_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request body for assigning a ticket to a support agent.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignTicketRequest {

    @NotBlank(message = "Assignee ID is required")
    private String assigneeId;

    @NotBlank(message = "Assignee name is required")
    private String assigneeName;

    private String assigneeDepartment;

    /** ID of the actor performing the assignment (for audit log). */
    @NotBlank(message = "Actor ID is required")
    private String actorId;

    private String actorName;
}
