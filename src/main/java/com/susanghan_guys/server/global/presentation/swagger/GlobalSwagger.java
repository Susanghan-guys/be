package com.susanghan_guys.server.global.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Global", description = "설정 확인을 위한 API")
public interface GlobalSwagger {
    @Operation(
            summary = "health check",
            description = "health check API"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "health check success"
            )
    })
    ResponseEntity<CommonResponse<String>> healthCheck();
}
