package com.awsfcaj.technical_support_backend.dto.request;

import com.awsfcaj.technical_support_backend.enums.Priority;
import com.awsfcaj.technical_support_backend.enums.SortDirection;
import com.awsfcaj.technical_support_backend.enums.TicketCategory;
import com.awsfcaj.technical_support_backend.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Query parameters for filtering and paginating the ticket list.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketFilterRequest {

    private TicketStatus status;
    private Priority priority;
    private TicketCategory category;

    /** Filter by assignee's user ID. */
    private String assigneeId;

    /** Filter by reporter's user ID. */
    private String reporterId;

    /** Filter overdue tickets only. */
    private Boolean overdueOnly;

    /** DynamoDB pagination token from previous response. */
    private String lastEvaluatedKey;

    /** Number of items per page (default: 20, max: 100). */
    @Builder.Default
    private int limit = 20;

    private SortDirection sortDirection;
}
