package com.susanghan_guys.server.work.domain;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.type.Brand;
import com.susanghan_guys.server.work.domain.type.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Work extends BaseEntity {

    @Id
    @Column(name = "work_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "member", nullable = false)
    private String member;

    @Column(name = "brand")
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id", nullable = false)
    private Contest contest;

    @Builder
    public Work(
            String title,
            String number,
            String member,
            Brand brand,
            Category category,
            Contest contest
    ) {
        this.title = title;
        this.number = number;
        this.member = member;
        this.brand = brand;
        this.category = category;
        this.contest = contest;
    }

    public void associateUser(User user) {
        this.user = user;
    }
}
