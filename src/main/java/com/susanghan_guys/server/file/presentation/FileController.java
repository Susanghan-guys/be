package com.susanghan_guys.server.file.presentation;

import com.susanghan_guys.server.file.application.PdfFileService;
import com.susanghan_guys.server.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.file.presentation.response.FileSuccessCode.DCA_PDF_CONVERT_SUCCESS;
import static com.susanghan_guys.server.file.presentation.response.FileSuccessCode.YCC_PDF_CONVERT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/file")
public class FileController {

    private final PdfFileService pdfFileService;

    // dca - test
    @PostMapping("/dca/{workId}")
    public CommonResponse<String> convertDcaPdfToImage(@PathVariable("workId") Long workId) {
        pdfFileService.convertDcaPdfToImage(workId);
        return CommonResponse.success(DCA_PDF_CONVERT_SUCCESS, "OK");
    }

    // ycc - test
    @PostMapping("/ycc/{workId}")
    public CommonResponse<String> convertToImage(@PathVariable("workId") Long workId) {
        pdfFileService.convertYccPdfToImage(workId);
        return CommonResponse.success(YCC_PDF_CONVERT_SUCCESS, "OK");
    }
}
