package com.susanghan_guys.server.work.application;

import com.susanghan_guys.server.global.s3.application.S3Service;
import com.susanghan_guys.server.work.domain.AdditionalFile;
import com.susanghan_guys.server.work.domain.PdfFile;
import com.susanghan_guys.server.work.domain.PdfImage;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.converter.PdfConverter;
import com.susanghan_guys.server.work.infrastructure.persistence.AdditionalFileRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.PdfFileRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.PdfImageRepository;
import com.susanghan_guys.server.work.infrastructure.saver.PdfFileSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfFileService {

    private final PdfFileSaver pdfFileSaver;
    private final S3Service s3Service;
    private final PdfConverter pdfConverter;
    private final AdditionalFileRepository additionalFileRepository;
    private final PdfFileRepository pdfFileRepository;
    private final PdfImageRepository pdfImageRepository;

    @Transactional
    public void convertDcaPdfToImage(Long workId) {
        AdditionalFile additionalFile = additionalFileRepository.findAdditionalFileByWorkId(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.ADDITIONAL_FILE_NOT_FOUND));

        PdfFile pdfFile = pdfFileRepository.findBySourceId(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        List<PdfImage> existingImageUrls = pdfImageRepository.findAllByPdfFile(pdfFile);
        if (!existingImageUrls.isEmpty()) {
            return;
        }

        List<byte[]> images = pdfConverter.convertPdfToImage(additionalFile.getValue());

        List<String> imageUrls = new ArrayList<>();
        for (byte[] image : images) {
            String imageUrl = s3Service.uploadPdfToImage(image, "dca-images");
            imageUrls.add(imageUrl);
        }
        pdfFileSaver.savePdfToImage(imageUrls, pdfFile);
    }

    @Transactional
    public void convertYccPdfToImage(Long workId) {
        PdfFile pdfFile = pdfFileRepository.findBySourceId(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        List<PdfImage> existingImageUrls = pdfImageRepository.findAllByPdfFile(pdfFile);
        if (!existingImageUrls.isEmpty()) {
            return;
        }

        List<byte[]> images = pdfConverter.convertPdfToImage(pdfFile.getFileUrl());

        List<String> imageUrls = new ArrayList<>();
        for (byte[] image : images) {
            String imageUrl = s3Service.uploadPdfToImage(image, "ycc-images");
            imageUrls.add(imageUrl);
        }
        pdfFileSaver.savePdfToImage(imageUrls, pdfFile);
    }
}
