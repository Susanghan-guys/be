package com.susanghan_guys.server.file.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "[백엔드 내부 - 파일 변환]", description = "DCA, YCC 작품 내 PDF -> IMAGE 변환 관련 API")
public interface FileSwagger {
    @Operation(
            summary = "DCA 작품 PDF -> IMAGE 변환 API",
            description = "DCA의 기획안을 Image로 변환합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "기획안 변환이 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<String> convertDcaPdfToImage(
            @PathVariable("workId") Long workId
    );

    @Operation(
            summary = "YCC 작품 PDF -> IMAGE 변환 API",
            description = "YCC의 기획안을 Image로 변환합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "기획안 변환이 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<String> convertYccPdfToImage(
            @PathVariable("workId") Long workId
    );
}
