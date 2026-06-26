package com.awsfcaj.technical_support_backend.dto.request;

import com.awsfcaj.technical_support_backend.enums.Priority;
import com.awsfcaj.technical_support_backend.enums.TicketCategory;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
<parameter name="CodeContent">package com.awsfcaj.technical_support_backend.dto.request;

import com.awsfcaj.technical_support_backend.enums.Priority;
import com.awsfcaj.technical_support_backend.enums.TicketCategory;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request body for updating ticket information (partial update).
 * All fields are optional — only non-null fields will be applied.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketRequest {

    @Size(min = 5, max = 200, message = "Title must be between 5 and 200 characters")
    private String title;

    @Size(min = 10, max = 5000, message = "Description must be between 10 and 5000 characters")
    private String description;

    private TicketCategory category;
    private Priority priority;

    private List<String> tags;
    private List<String> attachments;
}
