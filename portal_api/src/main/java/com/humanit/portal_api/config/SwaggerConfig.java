package com.humanit.portal_api.config;

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
        GroupedOpenApi portalApi() {
                return GroupedOpenApi.builder()
                        .group("portal-apis")
                        .pathsToMatch("/**")
                        .build();
        }

        @Bean
        OpenAPI customOpenAPI() {
                return new OpenAPI()
                        .info(new Info()
                                .title("Portal API")
                                .version("1.0")
                                .description("This microservice is designed to act as an intermediary between the " +
                                        "client and other external APIs, making HTTP requests authenticated with " +
                                        "Spring Security and JWT (JSON Web Token)."))
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                        .components(
                                new Components()
                                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")));
        }
}