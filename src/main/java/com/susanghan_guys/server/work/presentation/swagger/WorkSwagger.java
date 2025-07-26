package com.susanghan_guys.server.work.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.work.dto.request.DcaWorkSubmissionRequest;
import com.susanghan_guys.server.work.dto.request.YccWorkSubmissionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
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
            description = """
    **공통 필수 필드:**
    - title: 작품 제목
    - number: 출품 번호
    - category: 작품 카테고리 (예: FILM, VISUAL 등)
    - brand: 브랜드명
    - teamMembers: 팀원 정보 목록(없다면 필드 없애면 됩니다)
    - briefBoardFile: 브리프보드 이미지 (JPG, 최대 3508x4960px, 10MB)
    
    **추가자료 조건 분기:**
    
    [FILM 카테고리일 경우]
    - 유튜브 링크(youtubeUrl): **필수**
    - 기획서 파일 (additionalFile): 선택
 
    [FILM 외 카테고리일 경우]
    - **youtubeUrl 필드 사용 금지 (있으면 오류)**
    - 기획서 파일 (additionalFile): 선택

    ※ youtubeUrl, additionalFile의 존재 여부에 따라 자동으로 검증됩니다.
    \n※ 유튜브 링크와 기획서 파일은 유효성 검증을 거칩니다.
    \n※ 복잡한 부분이 있으므로 궁금한 점이 생기면 바로 문의해주세요.
    """
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
            summary = "YCC(HSAD) 공모전 작품 제출",
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