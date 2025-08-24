package com.susanghan_guys.server.feedback.presentation.swagger;

import com.susanghan_guys.server.feedback.dto.request.FeedbackRequest;
import com.susanghan_guys.server.global.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "[피드백]", description = "내 리포트 피드백 관련 API")
public interface FeedbackSwagger {
    @Operation(
            summary = "내 리포트 피드백 제출 API",
            description = """
                          ### RequestBody
                          ---
                          `score`: 만족도 점수 (1 ~ 5점 사이) \n
                          `content`: 리포트 후기
                          """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "내 리포트 피드백 제출이 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<String> createFeedback(
            @PathVariable Long workId,
            @RequestBody @Valid FeedbackRequest request
    );
}
