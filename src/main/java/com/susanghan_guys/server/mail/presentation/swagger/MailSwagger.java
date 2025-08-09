package com.susanghan_guys.server.mail.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "[메일]", description = "메일 전송 관련 API")
public interface MailSwagger {
    @Operation(
            summary = "완료된 리포트 메일 전송 API",
            description = "완료된 리포트의 신청자와 팀원에게 메일을 전송합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "메일 전송이 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<String> sendMail();
}
