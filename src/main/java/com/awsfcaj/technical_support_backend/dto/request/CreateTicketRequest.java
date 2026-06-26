package com.awsfcaj.technical_support_backend.dto.request;

import com.awsfcaj.technical_support_backend.enums.Priority;
import com.awsfcaj.technical_support_backend.enums.TicketCategory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request body for creating a new support ticket.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 200, message = "Title must be between 5 and 200 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 5000, message = "Description must be between 10 and 5000 characters")
    private String description;

    @NotNull(message = "Category is required")
    private TicketCategory category;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @NotBlank(message = "Reporter ID is required")
    private String reporterId;

    @NotBlank(message = "Reporter name is required")
    private String reporterName;

    @NotBlank(message = "Reporter email is required")
    @Email(message = "Reporter email must be valid")
    private String reporterEmail;

    private List<String> tags;
    private List<String> attachments;
}
