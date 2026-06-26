package com.awsfcaj.technical_support_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI / Swagger UI configuration.
 * Accessible at: /swagger-ui.html
 */
@Configuration
public class OpenApiConfig {

    @Value("${server.port:8080}")
    private int serverPort;

    @Bean
    public OpenAPI openAPI() {
        Server localServer = new Server()
                .url("http://localhost:" + serverPort)
                .description("Local Development");

        Contact contact = new Contact()
                .name("AWS FCAJ Team")
                .email("support@awsfcaj.com");

        Info info = new Info()
                .title("Technical Support Backend API")
                .version("1.0.0")
                .description("REST API for the Internal IT/Facility Technical Support Ticket System")
                .contact(contact)
                .license(new License().name("Proprietary"));

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}
