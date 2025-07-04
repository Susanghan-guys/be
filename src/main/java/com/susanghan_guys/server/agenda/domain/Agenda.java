package com.susanghan_guys.server.agenda.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    private String trendText;


}
