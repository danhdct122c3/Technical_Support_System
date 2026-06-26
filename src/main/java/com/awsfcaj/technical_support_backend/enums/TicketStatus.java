package com.awsfcaj.technical_support_backend.enums;

/**
 * Represents the lifecycle states of a support ticket.
 * Allowed transitions:
 *   OPEN → IN_PROGRESS → PENDING → RESOLVED → CLOSED
 *   RESOLVED → REOPENED → IN_PROGRESS
 */
public enum TicketStatus {

    /** Ticket has been submitted and awaits assignment. */
    OPEN,

    /** Ticket is actively being worked on by an assignee. */
    IN_PROGRESS,

    /** Ticket is waiting on a third party or more information. */
    PENDING,

    /** Issue has been resolved, pending confirmation. */
    RESOLVED,

    /** Ticket is fully closed (confirmed resolved). */
    CLOSED,

    /** Ticket was previously resolved but has been re-opened. */
    REOPENED
}
