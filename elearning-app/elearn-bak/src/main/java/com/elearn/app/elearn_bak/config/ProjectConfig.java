package com.elearn.app.elearn_bak.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(     //swagger api documentation information
    info = @Info(
        title = "Streaming app Backend",
        description = "Created by wigiwee",
        version = "1.01",
        contact = @Contact(
            name = "Wigiwee",
            email = "email@gmail.com",
            url = "abc.com"
        ),
        license = @License(
            url = "here goes the license url",
            name = "Apache license 2.0"
        )

    ),
    security = @SecurityRequirement(name = "jwtScheme")     //applying jwt scheme for all controllers
)
@SecurityScheme(
    name = "jwtScheme", 
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class ProjectConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
