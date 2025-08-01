package com.susanghan_guys.server.work.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PdfImage extends BaseEntity {

    @Id
    @Column(name = "pdf_image_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pdf_file_id", nullable = false)
    private PdfFile pdfFile;

    @Builder
    public PdfImage(String imageUrl, PdfFile pdfFile) {
        this.imageUrl = imageUrl;
        this.pdfFile = pdfFile;
    }
}
