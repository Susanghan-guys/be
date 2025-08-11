package com.susanghan_guys.server.personalwork.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.personalwork.domain.type.DetailEvalType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailEval extends BaseEntity {

    @Id
    @Column(name = "detail_eval_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "detail_eval_type", nullable = false)
    private DetailEvalType type;

    @Column(name = "score", nullable = false)
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation_id", nullable = false)
    private Evaluation evaluation;

    @Builder
    private DetailEval(
            String content,
            DetailEvalType type,
            int score,
            Evaluation evaluation
    ) {
        this.content = content;
        this.type = type;
        this.score = score;
        this.evaluation = evaluation;
    }

    public void associateEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
