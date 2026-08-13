package com.example.hospital.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
                                                                        
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hospital Management System API")
                        .version("1.0")
                        .description("REST API for Hospital Management System")
                      .contact(new Contact()
                                .name("Nazifa - Java Team")
                                .email("tnazifa003@gmail.com")
                               ));
    }
}