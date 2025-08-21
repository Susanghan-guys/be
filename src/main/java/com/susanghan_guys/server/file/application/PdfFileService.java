package com.susanghan_guys.server.file.application;

import com.susanghan_guys.server.file.exception.FileException;
import com.susanghan_guys.server.file.exception.code.FileErrorCode;
import com.susanghan_guys.server.file.infrastructure.mapper.PdfFileMapper;
import com.susanghan_guys.server.global.s3.application.S3Service;
import com.susanghan_guys.server.personalwork.application.port.PdfFilePort;
import com.susanghan_guys.server.file.domain.PdfFile;
import com.susanghan_guys.server.file.domain.PdfImage;
import com.susanghan_guys.server.file.infrastructure.converter.PdfConverter;
import com.susanghan_guys.server.file.infrastructure.persistence.PdfFileRepository;
import com.susanghan_guys.server.file.infrastructure.persistence.PdfImageRepository;
import com.susanghan_guys.server.file.infrastructure.saver.PdfFileSaver;
import com.susanghan_guys.server.file.domain.type.SourceType;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfFileService implements PdfFilePort {

    private final PdfFileSaver pdfFileSaver;
    private final S3Service s3Service;
    private final PdfConverter pdfConverter;
    private final PdfFileRepository pdfFileRepository;
    private final PdfImageRepository pdfImageRepository;
    private final WorkRepository workRepository;

    @Override
    @Transactional
    public List<PdfImage> convertDcaPdfToImage(Long workId) {
        // 추가 파일(기획안)이 없을 경우, 빈 리스트 반환
        PdfFile pdfFile = pdfFileRepository.findByWorkIdFromAdditionalFile(workId)
                .orElse(null);

        if (pdfFile == null) {
            return Collections.emptyList();
        }

        return convertAllPdfToImage(pdfFile, "dca-images");
    }

    @Override
    @Transactional
    public List<PdfImage> convertYccPdfToImage(Long workId) {
        PdfFile pdfFile = pdfFileRepository.findBySourceIdAndSourceType(workId, SourceType.WORK)
                .orElseGet(() -> {
                    // FIXME: 현재 제출된 작품이 모두 분석되면 필수로 제거해야 함.
                    Work work = workRepository.findById(workId)
                            .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));
                    PdfFile newPdfFile = PdfFileMapper.toEntity(work.getWork(), SourceType.WORK, work.getId());
                    return pdfFileRepository.save(newPdfFile);
                });

        return convertAllPdfToImage(pdfFile, "ycc-images");
    }

    private List<PdfImage> convertAllPdfToImage(PdfFile pdfFile, String bucketName) {
        List<PdfImage> existingImageUrls = pdfImageRepository.findAllByPdfFile(pdfFile);
        if (!existingImageUrls.isEmpty()) {
            return existingImageUrls;
        }

        List<byte[]> images = pdfConverter.convertPdfToImage(pdfFile.getFileUrl());

        List<String> imageUrls = new ArrayList<>();
        for (byte[] image : images) {
            String imageUrl = s3Service.uploadPdfToImage(image, bucketName);
            imageUrls.add(imageUrl);
        }
        return pdfFileSaver.savePdfImage(imageUrls, pdfFile);
    }
}
