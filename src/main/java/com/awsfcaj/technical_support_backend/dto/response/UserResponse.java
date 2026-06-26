package com.awsfcaj.technical_support_backend.dto.response;

import com.awsfcaj.technical_support_backend.enums.Department;
import com.awsfcaj.technical_support_backend.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * User detail response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String userId;
    private String email;
    private String fullName;
    private Department department;
    private UserRole role;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
