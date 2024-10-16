package com.ra.comprobante.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Comprobante CRUD",
                version = "1.0.0",
                description = "CRUD de administracion de comprobante"
        )
)
public class OpenApiConfig {
}
