package com.susanghan_guys.server.work.domain;

import com.susanghan_guys.server.work.domain.type.FilesType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "additional_files")
public class AdditionalFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id", nullable = false)
    private Work work;

    @Column(nullable = false)
    private String value; // URL 혹은 파일 S3 경로

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FilesType type;

    @Builder
    public AdditionalFile(Work work, String value, FilesType type) {
        this.work = work;
        this.value = value;
        this.type = type;
    }
}