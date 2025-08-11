package com.susanghan_guys.server.work.domain;

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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"work_id", "user_id"})
})
public class WorkVisibility extends BaseEntity {

    @Id
    @Column(name = "work_visibility_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id", nullable = false)
    private Work work;

    @Column(name = "visible", nullable = false)
    private boolean visible;

    @Builder
    private WorkVisibility(User user, Work work, boolean visible) {
        this.user = user;
        this.work = work;
        this.visible = visible;
    }

    public static WorkVisibility of(User user, Work work, boolean visible) {
        return WorkVisibility.builder()
                .user(user)
                .work(work)
                .visible(visible)
                .build();
    }
}
