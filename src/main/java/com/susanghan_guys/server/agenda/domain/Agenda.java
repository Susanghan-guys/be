package com.susanghan_guys.server.agenda.domain;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agenda extends BaseEntity {

    @Id
    @Column(name = "agenda_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trend_text", nullable = false)
    private String trendText;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id", nullable = false, unique = true)
    private Contest contest;

    @Builder
    public Agenda(
            String trendText,
            Contest contest
    ) {
        this.trendText = trendText;
        this.contest = contest;
    }
}
