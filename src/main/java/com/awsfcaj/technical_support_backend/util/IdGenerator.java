package com.awsfcaj.technical_support_backend.util;

import java.util.UUID;

/**
 * Utility class for generating unique identifiers.
 */
public final class IdGenerator {

    private IdGenerator() {}

    /**
     * Generates a random UUID v4 string (without hyphens).
     * Example: "550e8400e29b41d4a716446655440000"
     */
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Generates a random UUID v4 string (with hyphens).
     * Example: "550e8400-e29b-41d4-a716-446655440000"
     */
    public static String generateWithHyphens() {
        return UUID.randomUUID().toString();
    }
}
