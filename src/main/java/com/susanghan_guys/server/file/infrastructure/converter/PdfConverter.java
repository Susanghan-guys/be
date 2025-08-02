package com.susanghan_guys.server.file.infrastructure.converter;

import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PdfConverter {

    public List<byte[]> convertPdfToImage(String pdfUrl) {
        List<byte[]> imageUrls = new ArrayList<>();

        try (InputStream inputStream = new URL(pdfUrl).openStream();
             PDDocument document = PDDocument.load(inputStream)) {

            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 100, ImageType.RGB);

                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    ImageIO.write(image, "jpg", outputStream);
                    imageUrls.add(outputStream.toByteArray());
                }
            }
            return imageUrls;
        } catch (IOException e) {
            log.error("🚨 PDF -> JPG 변환 실패: {}", e.getMessage());
            throw new WorkException(WorkErrorCode.PDF_TO_IMAGE_FAILED);
        }
    }
}
