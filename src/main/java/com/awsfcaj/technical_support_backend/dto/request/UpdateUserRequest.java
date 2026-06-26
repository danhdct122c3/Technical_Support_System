package com.awsfcaj.technical_support_backend.dto.request;

import com.awsfcaj.technical_support_backend.enums.Department;
import com.awsfcaj.technical_support_backend.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request body for updating an existing user (partial update).
 * All fields are optional — only non-null fields will be applied.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String fullName;
    private Department department;
    private UserRole role;
    private Boolean isActive;
}
