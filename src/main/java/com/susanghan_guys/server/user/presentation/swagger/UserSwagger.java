package com.susanghan_guys.server.user.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.user.dto.request.MyPageInfoRequest;
import com.susanghan_guys.server.user.dto.request.UserOnboardingRequest;
import com.susanghan_guys.server.user.dto.request.UserTermsRequest;
import com.susanghan_guys.server.user.dto.request.UserWithdrawalRequest;
import com.susanghan_guys.server.user.dto.response.MyPageInfoResponse;
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

    @Operation(
            summary = "사용자 마이 페이지 조회 API",
            description = "사용자 마이 페이지를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 마이 페이지 조회를 성공적으로 실행하였습니다."
            )
    })
    CommonResponse<MyPageInfoResponse> getMyPageInfo ();

    @Operation(
            summary = "사용자 마이 페이지 수정 API",
            description = "사용자 마이 페이지를 수정합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 마이 페이지 수정을 성공적으로 실행하였습니다."
            )
    })
    CommonResponse<MyPageInfoResponse> updateMyPageInfo (
            @RequestBody @Valid MyPageInfoRequest request
    );

    @Operation(
            summary = "사용자 탈퇴 API",
            description = """
                          ### RequestBody
                          ---
                          `withdrawalReason`: 사용자 탈퇴 이유 (ENUM) \n
                          `etc`: 기타를 선택할 시, 작성 (String)
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 탈퇴가 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<String> withdrawUser(
            @RequestBody @Valid UserWithdrawalRequest request
    );
}
