package com.awsfcaj.technical_support_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.Instant;

/**
 * DynamoDB entity representing a system user (reporter or support agent).
 *
 * Table: tbs-users
 * PK: userId (String, UUID)
 *
 * GSI:
 *   - EmailIndex: email (PK)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class User {

    private String userId;
    private String email;
    private String fullName;
    private String department;   // Department enum value
    private String role;         // UserRole enum value
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("userId")
    public String getUserId() {
        return userId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "EmailIndex")
    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }
}
