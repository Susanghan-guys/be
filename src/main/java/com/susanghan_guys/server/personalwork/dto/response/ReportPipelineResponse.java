package com.susanghan_guys.server.personalwork.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "리포트 생성 파이프라인 결과")
public record ReportPipelineResponse(
        @Schema(description = "요약 완료 여부")
        boolean summaryDone,

        @Schema(description = "브리프 해석 완료 여부 (DCA만)")
        boolean briefDone,

        @Schema(description = "총평 완료 여부")
        boolean evaluationDone
) {}