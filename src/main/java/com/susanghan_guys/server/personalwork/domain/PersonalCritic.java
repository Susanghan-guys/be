package com.susanghan_guys.server.personalwork.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalCritic extends BaseEntity {

    @Id
    @Column(name = "personal_critic_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_work_id", nullable = false)
    private PersonalWork personalWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "critic_id", nullable = false)
    private Critic critic;

    @Builder
    public PersonalCritic(
            PersonalWork personalWork,
            Critic critic
    ) {
        this.personalWork = personalWork;
        this.critic = critic;
    }
}
