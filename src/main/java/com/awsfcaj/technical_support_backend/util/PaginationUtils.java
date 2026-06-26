package com.awsfcaj.technical_support_backend.util;

import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Map;

/**
 * Utility class for DynamoDB cursor-based pagination.
 *
 * DynamoDB returns a LastEvaluatedKey map on paginated results.
 * This utility serialises/deserialises that map to/from a Base64 string
 * suitable for HTTP query parameters.
 *
 * Note: A production-grade implementation should use Jackson ObjectMapper
 * for proper JSON serialisation of the key map.
 */
public final class PaginationUtils {

    private PaginationUtils() {}

    /**
     * Encodes a LastEvaluatedKey map to a Base64 string token.
     * Returns null if the map is null or empty (no more pages).
     */
    public static String encodeToken(Map<String, ?> lastEvaluatedKey) {
        if (lastEvaluatedKey == null || lastEvaluatedKey.isEmpty()) {
            return null;
        }
        // Simplified: stringify the map and base64-encode
        String raw = lastEvaluatedKey.toString();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(raw.getBytes());
    }

    /**
     * Decodes a Base64 pagination token back to a string representation.
     * Returns null if the token is blank.
     */
    public static String decodeToken(String token) {
        if (!StringUtils.hasText(token)) {
            return null;
        }
        byte[] decoded = Base64.getUrlDecoder().decode(token);
        return new String(decoded);
    }

    /**
     * Validates that the requested page size is within allowed bounds.
     *
     * @param limit    the requested limit
     * @param maxLimit the maximum allowed limit
     * @return the validated limit (clamped to [1, maxLimit])
     */
    public static int clampLimit(int limit, int maxLimit) {
        return Math.max(1, Math.min(limit, maxLimit));
    }
}
