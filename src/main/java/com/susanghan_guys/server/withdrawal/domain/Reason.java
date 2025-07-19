package com.susanghan_guys.server.withdrawal.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.withdrawal.domain.type.ReasonType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reason extends BaseEntity {

    @Id
    @Column(name = "reason_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "reason_types",
            joinColumns = @JoinColumn(name = "reason_id")
    )
    @Column(name = "withdrawal_reason")
    private List<ReasonType> reasonTypes;

    @Column(name = "etc")
    private String etc;

    @Builder
    public Reason(
            String userName,
            List<ReasonType> reasonTypes,
            String etc
    ) {
        this.userName = userName;
        this.reasonTypes = reasonTypes;
        this.etc = etc;
    }
}
