package com.susanghan_guys.server.globals.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "수상한 녀석들 API 명세서",
                description = "수상한 녀석들 API 명세서",
                version = "v1"
        ),
        servers = @Server(description = "Default Server URL")
)
public class SwaggerConfig {
}
