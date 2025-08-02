package com.susanghan_guys.server.file.application;

import com.susanghan_guys.server.global.s3.application.S3Service;
import com.susanghan_guys.server.personalwork.application.port.PdfFilePort;
import com.susanghan_guys.server.file.domain.PdfFile;
import com.susanghan_guys.server.file.domain.PdfImage;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.file.infrastructure.converter.PdfConverter;
import com.susanghan_guys.server.work.infrastructure.persistence.AdditionalFileRepository;
import com.susanghan_guys.server.file.infrastructure.persistence.PdfFileRepository;
import com.susanghan_guys.server.file.infrastructure.persistence.PdfImageRepository;
import com.susanghan_guys.server.file.infrastructure.saver.PdfFileSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfFileService implements PdfFilePort {

    private final PdfFileSaver pdfFileSaver;
    private final S3Service s3Service;
    private final PdfConverter pdfConverter;
    private final AdditionalFileRepository additionalFileRepository;
    private final PdfFileRepository pdfFileRepository;
    private final PdfImageRepository pdfImageRepository;

    @Override
    @Transactional
    public List<PdfImage> convertDcaPdfToImage(Long workId) {
        PdfFile pdfFile = pdfFileRepository.findByWorkIdFromAdditionalFile(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND)); // TODO

        List<PdfImage> existingImageUrls = pdfImageRepository.findAllByPdfFile(pdfFile);
        if (!existingImageUrls.isEmpty()) {
            return existingImageUrls;
        }

        List<byte[]> images = pdfConverter.convertPdfToImage(pdfFile.getFileUrl());

        List<String> imageUrls = new ArrayList<>();
        for (byte[] image : images) {
            String imageUrl = s3Service.uploadPdfToImage(image, "dca-images");
            imageUrls.add(imageUrl);
        }
        return pdfFileSaver.savePdfImage(imageUrls, pdfFile);
    }

    @Override
    @Transactional
    public List<PdfImage> convertYccPdfToImage(Long workId) {
        PdfFile pdfFile = pdfFileRepository.findBySourceId(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND)); // TODO

        List<PdfImage> existingImageUrls = pdfImageRepository.findAllByPdfFile(pdfFile);
        if (!existingImageUrls.isEmpty()) {
            return existingImageUrls;
        }

        List<byte[]> images = pdfConverter.convertPdfToImage(pdfFile.getFileUrl());

        List<String> imageUrls = new ArrayList<>();
        for (byte[] image : images) {
            String imageUrl = s3Service.uploadPdfToImage(image, "ycc-images");
            imageUrls.add(imageUrl);
        }
        return pdfFileSaver.savePdfImage(imageUrls, pdfFile);
    }
}
