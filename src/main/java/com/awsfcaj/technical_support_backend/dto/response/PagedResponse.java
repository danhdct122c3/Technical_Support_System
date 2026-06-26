package com.awsfcaj.technical_support_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Generic pagination wrapper for list endpoints.
 *
 * <pre>
 * {
 *   "items": [...],
 *   "count": 10,
 *   "lastEvaluatedKey": "base64token...",
 *   "hasMore": true
 * }
 * </pre>
 *
 * @param <T> the item type
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> {

    private List<T> items;

    /** Number of items in this page. */
    private int count;

    /** DynamoDB pagination token for the next page; null if this is the last page. */
    private String lastEvaluatedKey;

    /** Convenience flag: true when lastEvaluatedKey is non-null. */
    private boolean hasMore;

    public static <T> PagedResponse<T> of(List<T> items, String lastEvaluatedKey) {
        return PagedResponse.<T>builder()
                .items(items)
                .count(items.size())
                .lastEvaluatedKey(lastEvaluatedKey)
                .hasMore(lastEvaluatedKey != null)
                .build();
    }
}
