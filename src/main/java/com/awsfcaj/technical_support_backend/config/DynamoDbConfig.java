package com.awsfcaj.technical_support_backend.config;

import com.awsfcaj.technical_support_backend.entity.AuditLog;
import com.awsfcaj.technical_support_backend.entity.Comment;
import com.awsfcaj.technical_support_backend.entity.Ticket;
import com.awsfcaj.technical_support_backend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

import java.net.URI;

/**
 * DynamoDB configuration.
 *
 * For local development, set aws.dynamodb.endpoint to "http://localhost:8000".
 * For AWS deployment, leave aws.dynamodb.endpoint empty — IAM role credentials are used.
 */
@Configuration
public class DynamoDbConfig {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.dynamodb.endpoint:}")
    private String dynamoDbEndpoint;

    @Value("${aws.dynamodb.table-prefix:tbs-}")
    private String tablePrefix;

    @Bean
    public DynamoDbClient dynamoDbClient() {
        DynamoDbClientBuilder builder = DynamoDbClient.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create());

        if (dynamoDbEndpoint != null && !dynamoDbEndpoint.isBlank()) {
            builder.endpointOverride(URI.create(dynamoDbEndpoint));
        }

        return builder.build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    @Bean
    public DynamoDbTable<Ticket> ticketTable(DynamoDbEnhancedClient enhancedClient) {
        return enhancedClient.table(tablePrefix + "tickets", TableSchema.fromBean(Ticket.class));
    }

    @Bean
    public DynamoDbTable<Comment> commentTable(DynamoDbEnhancedClient enhancedClient) {
        return enhancedClient.table(tablePrefix + "comments", TableSchema.fromBean(Comment.class));
    }

    @Bean
    public DynamoDbTable<User> userTable(DynamoDbEnhancedClient enhancedClient) {
        return enhancedClient.table(tablePrefix + "users", TableSchema.fromBean(User.class));
    }

    @Bean
    public DynamoDbTable<AuditLog> auditLogTable(DynamoDbEnhancedClient enhancedClient) {
        return enhancedClient.table(tablePrefix + "audit-logs", TableSchema.fromBean(AuditLog.class));
    }
}
