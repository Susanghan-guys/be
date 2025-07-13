package com.susanghan_guys.server.user.presentation.swagger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.user.dto.request.RefreshTokenRequest;
import com.susanghan_guys.server.user.dto.response.ExchangeTokenResponse;
import com.susanghan_guys.server.user.dto.response.RefreshTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "[사용자 인증]", description = "사용자 로그아웃, 토큰 재발급 관련 API")
public interface UserAuthSwagger {
    @Operation(
            summary = "사용자 토큰 및 정보 조회 API",
            description = "발급된 임시 코드를 통해 사용자의 accessToken, refreshToken 및 정보를 반환합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 토큰 및 정보 조회가 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<ExchangeTokenResponse> exchangeToken(
            @RequestParam String code
    );

    @Operation(
            summary = "사용자 로그아웃 API",
            description = "사용자 로그아웃을 진행합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 로그아웃이 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<String> logout(HttpServletRequest request);

    @Operation(
            summary = "refreshToken 재발급 API",
            description = "refreshToken 재발급을 진행합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "refreshToken 재발급이 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<RefreshTokenResponse> updateRefreshToken(
            @RequestBody @Valid RefreshTokenRequest request
    );
}
