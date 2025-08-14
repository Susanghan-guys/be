package com.susanghan_guys.server.file.presentation;

import com.susanghan_guys.server.file.application.PdfFileService;
import com.susanghan_guys.server.file.presentation.swagger.FileSwagger;
import com.susanghan_guys.server.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.file.presentation.response.FileSuccessCode.DCA_CONVERT_SUCCESS;
import static com.susanghan_guys.server.file.presentation.response.FileSuccessCode.YCC_CONVERT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/files")
public class FileController implements FileSwagger {

    private final PdfFileService pdfFileService;

    @Override
    @PostMapping("/dca/{workId}")
    public CommonResponse<String> convertDcaPdfToImage(@PathVariable("workId") Long workId) {
        pdfFileService.convertDcaPdfToImage(workId);
        return CommonResponse.success(DCA_CONVERT_SUCCESS, "OK");
    }

    @Override
    @PostMapping("/ycc/{workId}")
    public CommonResponse<String> convertYccPdfToImage(@PathVariable("workId") Long workId) {
        pdfFileService.convertYccPdfToImage(workId);
        return CommonResponse.success(YCC_CONVERT_SUCCESS, "OK");
    }
}
