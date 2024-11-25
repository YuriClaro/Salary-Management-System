package com.humanit.email_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Bean
        GroupedOpenApi emailApi() {
                return GroupedOpenApi.builder()
                        .group("email-apis")
                        .pathsToMatch("/**")
                        .build();
        }

        @Bean
        OpenAPI customOpenAPI() {
                return new OpenAPI()
                        .info(new Info()
                                .title("email API")
                                .version("1.0")
                                .description("This API is a microservice responsible for receiving Excel files from " +
                                        "the salary microservice generating email messages with the attached Excel " +
                                        "files, and sending these emails to the logged-in user.")
                        )
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .components(
                                new Components()
                                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")));
        }
}