package com.susanghan_guys.server.work.domain;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.type.Brand;
import com.susanghan_guys.server.work.domain.type.Category;
import com.susanghan_guys.server.work.domain.type.ReportStatus;
import com.susanghan_guys.server.work.domain.type.WorkType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "work", nullable = false)
    private String work;

    @Column(name = "number")
    private String number; // DCA

    @Column(name = "brand")
    @Enumerated(EnumType.STRING)
    private Brand brand; // DCA

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category; // DCA

    @Column(name = "work_type")
    @Enumerated(EnumType.STRING)
    private WorkType type;

    @Column(name = "report_status")
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

    @Column(name = "code")
    private String code;

    @Column(name = "mail_sent_at")
    private LocalDateTime mailSentAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id", nullable = false)
    private Contest contest;

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdditionalFile> additionalFiles = new ArrayList<>();

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkMember> workMembers = new ArrayList<>();

    @Builder
    public Work(
            String title,
            String number,
            Brand brand,
            Category category,
            WorkType type,
            String code,
            LocalDateTime mailSentAt,
            ReportStatus reportStatus,
            String work,
            User user,
            Contest contest
    ) {
        this.title = title;
        this.number = number;
        this.brand = brand;
        this.category = category;
        this.type = type;
        this.code = code;
        this.mailSentAt = mailSentAt;
        this.reportStatus = reportStatus;
        this.work = work;
        this.user = user;
        this.contest = contest;
    }

    public void addWorkMember(WorkMember workMember) {
        this.workMembers.add(workMember);
    }

    public void associateUser(User user) {
        this.user = user;
        user.getWorkList().add(this);
    }

    public void updateCode(String code) {
        this.code = code;
    }

    public void markMailSent() {
        this.mailSentAt = LocalDateTime.now();
    }

    public void updateReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }
}
