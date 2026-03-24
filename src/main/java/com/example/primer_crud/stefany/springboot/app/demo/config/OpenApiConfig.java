package com.example.primer_crud.stefany.springboot.app.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI customOpenAPI() {

                return new OpenAPI()
                                // Indica que la API usa seguridad
                                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))

                                // Define el esquema JWT
                                .components(new Components()
                                                .addSecuritySchemes("BearerAuth",
                                                                new SecurityScheme()
                                                                                .name("Authorization")
                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer")
                                                                                .bearerFormat("JWT")));
        }
}
