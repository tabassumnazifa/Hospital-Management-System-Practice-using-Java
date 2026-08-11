package com.example.hospital.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI hospitalOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Hospital Management API")
                .description("My first professional layered Spring Boot app")
                .version("1.0"));
    }
}