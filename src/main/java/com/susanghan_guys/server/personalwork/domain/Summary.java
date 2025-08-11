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
public class Summary extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "insight", nullable = false)
    private String insight;

    @Column(name = "solution", nullable = false)
    private String solution;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id", nullable = false, unique = true)
    private Work work;

    @Builder
    public Summary(
            String target,
            String insight,
            String solution,
            Work work
    ) {
        this.target = target;
        this.insight = insight;
        this.solution = solution;
        this.work = work;
    }
}
