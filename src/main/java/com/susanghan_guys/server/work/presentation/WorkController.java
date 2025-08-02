package com.susanghan_guys.server.work.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.work.application.DcaWorkService;
import com.susanghan_guys.server.work.application.PdfFileService;
import com.susanghan_guys.server.work.application.YccWorkService;
import com.susanghan_guys.server.work.dto.request.DcaWorkSubmissionRequest;
import com.susanghan_guys.server.work.dto.request.YccWorkSubmissionRequest;
import com.susanghan_guys.server.work.presentation.swagger.WorkSwagger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.susanghan_guys.server.work.presentation.response.WorkSuccessCode.WORK_DCA_SUBMIT_SUCCESS;
import static com.susanghan_guys.server.work.presentation.response.WorkSuccessCode.WORK_YCC_SUBMIT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/works")
public class WorkController implements WorkSwagger {

    private final DcaWorkService dcaWorkService;
    private final YccWorkService yccWorkService;
    private final PdfFileService pdfImageService;

    @Override
    @PostMapping(value = "/dca", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<String> submitDca(
            @RequestPart @Valid DcaWorkSubmissionRequest request,
            @RequestPart MultipartFile briefBoardFile,
            @RequestPart(required = false) MultipartFile additionalFile
    ) {
        dcaWorkService.submit(request, briefBoardFile, additionalFile);
        return CommonResponse.success(WORK_DCA_SUBMIT_SUCCESS, "OK");
    }

    @Override
    @PostMapping(value = "/ycc", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<String> submitYcc(
            @RequestPart @Valid YccWorkSubmissionRequest request,
            @RequestPart MultipartFile planFile
    ) {
        yccWorkService.submit(request, planFile);
        return CommonResponse.success(WORK_YCC_SUBMIT_SUCCESS, "OK");
    }

    // dca - test
    @PostMapping("/dca/{workId}")
    public CommonResponse<String> convertDcaPdfToImage(@PathVariable("workId") Long workId) {
        pdfImageService.convertDcaPdfToImage(workId);
        return CommonResponse.success(WORK_DCA_SUBMIT_SUCCESS, "OK");
    }

    // ycc - test
    @PostMapping("/ycc/{workId}")
    public CommonResponse<String> convertToImage(@PathVariable("workId") Long workId) {
        pdfImageService.convertYccPdfToImage(workId);
        return CommonResponse.success(WORK_YCC_SUBMIT_SUCCESS, "OK");
    }
}