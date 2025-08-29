package com.susanghan_guys.server.file.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.file.domain.type.SourceType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"source_type", "source_id"})
})
public class PdfFile extends BaseEntity {

    @Id
    @Column(name = "pdf_file_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "source_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;

    @Column(name = "source_id", nullable = false)
    private Long sourceId;

    @OneToMany(mappedBy = "pdfFile", cascade = CascadeType.ALL)
    private List<PdfImage> pdfImages = new ArrayList<>();

    @Builder
    public PdfFile(
            String fileUrl,
            SourceType sourceType,
            Long sourceId
    ) {
        this.fileUrl = fileUrl;
        this.sourceType = sourceType;
        this.sourceId = sourceId;
    }
}
