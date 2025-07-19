package com.susanghan_guys.server.work.domain;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.type.Brand;
import com.susanghan_guys.server.work.domain.type.Category;
import com.susanghan_guys.server.work.domain.type.FilesType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id", nullable = false)
    private Work work;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public WorkMember(Work work, Member member) {
        this.work = work;
        this.member = member;
        this.work.addWorkMember(this);
        this.member.getWorkMembers().add(this);
    }
}
