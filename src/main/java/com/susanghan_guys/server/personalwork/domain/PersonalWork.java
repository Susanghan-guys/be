package com.susanghan_guys.server.personalwork.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.personalwork.domain.type.ItemType;
import com.susanghan_guys.server.work.domain.Work;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalWork extends BaseEntity {

    @Id
    @Column(name = "personal_work_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "score")
    private Integer score;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id", nullable = false, unique = true)
    private Work work;

    @Builder
    public PersonalWork(
            ItemType itemType,
            String title,
            String subtitle,
            String content,
            Integer score,
            Work work
    ) {
        this.itemType = itemType;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.score = score;
        this.work = work;
    }
}
