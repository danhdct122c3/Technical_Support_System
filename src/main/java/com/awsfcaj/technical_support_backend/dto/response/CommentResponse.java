package com.awsfcaj.technical_support_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Comment detail response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private String commentId;
    private String ticketId;
    private String authorId;
    private String authorName;
    private String content;
    private Boolean isInternal;
    private Instant createdAt;
    private Instant updatedAt;
}
