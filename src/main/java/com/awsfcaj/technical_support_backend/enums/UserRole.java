package com.awsfcaj.technical_support_backend.enums;

/**
 * Role of a system user, controls permission levels.
 */
public enum UserRole {

    /** Standard employee who can create and view own tickets. */
    USER,

    /** Support agent who can be assigned tickets and update status. */
    AGENT,

    /** Department supervisor who can manage agents and view all tickets. */
    SUPERVISOR,

    /** Full system administrator. */
    ADMIN
}
