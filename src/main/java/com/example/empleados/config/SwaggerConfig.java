package com.example.empleados.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Empleados")
                        .version("1.0")
                        .description("API REST para operaciones CRUD de empleados")
                        .contact(new Contact()
                                .name("Soporte")
                                .email("soporte@empresa.com")));
    }
}