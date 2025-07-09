package com.susanghan_guys.server.terms.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTerms extends BaseEntity {

    @Id
    @Column(name = "user_terms_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id", nullable = false)
    private Terms terms;

    @Builder
    public UserTerms(
            User user,
            Terms terms
    ) {
        this.user = user;
        this.terms = terms;
    }
}
