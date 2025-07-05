package com.susanghan_guys.server.contest.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contest extends BaseEntity {

    @Id
    @Column(name = "contest_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "period", nullable = false)
    private LocalDate period;

    @Column(name = "result_date", nullable = false)
    private LocalDate resultDate;

    @Column(name = "logo", nullable = false)
    private String logo;

    @Builder
    public Contest(
            String title,
            LocalDate period,
            LocalDate resultDate,
            String logo
    ) {
        this.title = title;
        this.period = period;
        this.resultDate = resultDate;
        this.logo = logo;
    }
}
