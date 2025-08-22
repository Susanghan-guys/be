package com.susanghan_guys.server.personalwork.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.work.domain.Work;
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
public class Evaluation extends BaseEntity {

    @Id
    @Column(name = "evaluation_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "score")
    private double score;

    @Enumerated(EnumType.STRING)
    @Column(name = "evaluation_type", length = 50, nullable = false)
    private EvaluationType type;

    @OneToMany(mappedBy = "evaluation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailEval> details = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id", nullable = false)
    private Work work;

    @Builder
    public Evaluation(
            String content,
            double score,
            EvaluationType type,
            Work work
    ) {
        this.content = content;
        this.score = score;
        this.type = type;
        this.work = work;
    }

    public void addDetail(DetailEval detailEval) {
        this.details.add(detailEval);
        detailEval.associateEvaluation(this);
    }
}
