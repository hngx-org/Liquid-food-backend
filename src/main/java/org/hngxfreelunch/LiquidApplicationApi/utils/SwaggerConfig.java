package org.hngxfreelunch.LiquidApplicationApi.utils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        SecurityScheme securityScheme  = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("jwt",securityScheme))
                .info(new Info()
                        .title("FREE LUNCH - TEAM LIQUID")
                        .version("v1"))
                .security(Collections.singletonList(new SecurityRequirement().addList("jwt")));
    }
}
