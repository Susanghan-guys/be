package com.susanghan_guys.server.contest.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    private String title;

    private LocalDate period;

    private LocalDate resultDate;

    private String logo;
}
