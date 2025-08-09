package com.susanghan_guys.server.work.domain;

import com.susanghan_guys.server.work.domain.type.ReportVisibility;
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

    @Column(name = "report_visibility")
    @Enumerated(EnumType.STRING)
    private ReportVisibility reportVisibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id", nullable = false)
    private Work work;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_member_id", nullable = false)
    private TeamMember teamMember;

    @Builder
    public WorkMember(ReportVisibility reportVisibility, Work work, TeamMember teamMember) {
        this.reportVisibility = reportVisibility;
        this.work = work;
        this.teamMember = teamMember;
        this.work.addWorkMember(this);
        this.teamMember.getWorkMembers().add(this);
    }

    public void updateVisibility(ReportVisibility reportVisibility) {
        this.reportVisibility = reportVisibility;
    }
}
