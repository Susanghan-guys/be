package com.susanghan_guys.server.user.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.user.dto.request.UserOnboardingRequest;
import com.susanghan_guys.server.user.dto.request.UserTermsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "[사용자]", description = "사용자 관련 API")
public interface UserSwagger {
    @Operation(
            summary = "사용자 이용 약관 동의 API",
            description = "사용자 이용 약관 동의를 진행합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 이용 약관 동의가 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<String> saveUserAgreement(
            @RequestBody @Valid UserTermsRequest request
    );

    @Operation(
            summary = "사용자 온보딩 API",
            description = "사용자 온보딩을 진행합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 온보딩이 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<String> saveUserOnboarding (
            @RequestBody @Valid UserOnboardingRequest request
    );
}
