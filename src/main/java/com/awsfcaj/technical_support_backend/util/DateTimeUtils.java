package com.awsfcaj.technical_support_backend.util;

import com.awsfcaj.technical_support_backend.enums.Priority;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for SLA-based due date calculations.
 */
public final class DateTimeUtils {

    private DateTimeUtils() {}

    /**
     * Calculates the due date (Instant) based on ticket priority and creation time.
     *
     * SLA:
     * - CRITICAL → 4 hours
     * - HIGH     → 24 hours
     * - MEDIUM   → 72 hours
     * - LOW      → 168 hours (7 days)
     *
     * @param createdAt the time the ticket was created
     * @param priority  the ticket priority
     * @return the calculated due date
     */
    public static Instant calculateDueAt(Instant createdAt, Priority priority) {
        long hours = switch (priority) {
            case CRITICAL -> 4;
            case HIGH     -> 24;
            case MEDIUM   -> 72;
            case LOW      -> 168;
        };
        return createdAt.plus(hours, ChronoUnit.HOURS);
    }

    /**
     * Returns true if the given instant is in the past.
     */
    public static boolean isOverdue(Instant dueAt) {
        return dueAt != null && Instant.now().isAfter(dueAt);
    }
}
