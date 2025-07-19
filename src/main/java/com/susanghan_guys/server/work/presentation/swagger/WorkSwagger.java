package com.susanghan_guys.server.work.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.work.dto.DcaWorkSubmissionRequest;
import com.susanghan_guys.server.work.dto.YccWorkSubmissionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "[작품 제출]", description = "DCA, YCC 공모전 작품 제출 API")
@RestController
public interface WorkSwagger {

    @Operation(
            summary = "DCA 공모전 작품 제출",
            description = "브리프보드와 추가자료(링크 또는 파일)를 함께 제출합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DCA 작품 제출 성공")
    })
    @RequestBody(content = @Content(
            encoding = {
                    @Encoding(name = "request", contentType = MediaType.APPLICATION_JSON_VALUE),
                    @Encoding(name = "briefBoardFile", contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE),
                    @Encoding(name = "additionalFile", contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE)
            }
    ))
    @PostMapping(value = "/v1/works/dca", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CommonResponse<String> submitDca(
            @RequestPart("request") DcaWorkSubmissionRequest request,
            @RequestPart("briefBoardFile") MultipartFile briefBoardFile,
            @RequestPart(value = "additionalFile", required = false) MultipartFile additionalFile
    );

    @Operation(
            summary = "YCC(HASD) 공모전 작품 제출",
            description = "기획서 파일만 제출합니다 (pdf/ppt/pptx, 최대 10MB)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "YCC 작품 제출 성공")
    })
    @RequestBody(content = @Content(
            encoding = {
                    @Encoding(name = "request", contentType = MediaType.APPLICATION_JSON_VALUE),
                    @Encoding(name = "planFile", contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE)
            }
    ))
    @PostMapping(value = "/v1/works/ycc", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CommonResponse<String> submitYcc(
            @RequestPart("request") YccWorkSubmissionRequest request,
            @RequestPart("planFile") MultipartFile planFile
    );
}