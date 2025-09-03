package com.susanghan_guys.server.personalwork.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.work.domain.Work;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BriefAnalysis extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interpretation", nullable = false)
    private String interpretation;

    @Column(name = "consistency", nullable = false, length = 300)
    private String consistency;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id", nullable = false, unique = true)
    private Work work;

    @Builder
    public BriefAnalysis(
            String interpretation,
            String consistency,
            Work work
    ) {
        this.interpretation = interpretation;
        this.consistency = consistency;
        this.work = work;
    }
}
