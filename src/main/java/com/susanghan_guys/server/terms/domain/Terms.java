package com.susanghan_guys.server.terms.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms extends BaseEntity {

    @Id
    @Column(name = "terms_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_required", nullable = false)
    private boolean isRequired;

    @Builder
    public Terms(
            String title,
            String content,
            boolean isRequired
    ) {
        this.title = title;
        this.content = content;
        this.isRequired = isRequired;
    }
}
