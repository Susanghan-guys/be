package com.susanghan_guys.server.personalwork.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.personalwork.domain.type.CriticType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Critic extends BaseEntity {

    @Id
    @Column(name = "critic_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "critic_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CriticType criticType;

    @Column(name = "title", nullable = false)
    private String title;

    @Builder
    public Critic(
            CriticType criticType,
            String title
    ) {
        this.criticType = criticType;
        this.title = title;
    }
}
