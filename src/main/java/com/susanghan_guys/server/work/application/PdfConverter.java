package com.susanghan_guys.server.work.application;

import com.susanghan_guys.server.global.s3.application.S3Service;
import com.susanghan_guys.server.work.domain.PdfImage;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.infrastructure.persistence.PdfImageRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Slf4j
@Component
@RequiredArgsConstructor
public class PdfConverter {

    private final WorkRepository workRepository;
    private final PdfImageRepository pdfImageRepository;
    private final S3Service s3Service;

    public void convertToYccImage(Long workId) {
        Work work = workRepository.findById(workId).orElse(null);
        String pdfUrl = workRepository.findYccByWorkByWorkId(workId);

        try {
            try (InputStream inputStream = new URL(pdfUrl).openStream()) {
                PDDocument document = PDDocument.load(inputStream);
                PDFRenderer pdfRenderer = new PDFRenderer(document);

                for (int i = 0; i < document.getNumberOfPages(); i++) {
                    BufferedImage image = pdfRenderer.renderImageWithDPI(i, 100, ImageType.RGB);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    ImageIO.write(image, "jpg", outputStream);
                    byte[] bytes = outputStream.toByteArray();

                    String imageUrl = s3Service.uploadPdfToImage(bytes, "ycc-images");

                    pdfImageRepository.save(new PdfImage(imageUrl, work));
                }
            }
        } catch (IOException e) {
            log.error("🚨 YCC PDF -> JPG 변환 실패: {}", e.getMessage());
        }
    }
}
