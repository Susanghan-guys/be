package com.susanghan_guys.server.user.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.user.domain.type.Channel;
import com.susanghan_guys.server.user.domain.type.Purpose;
import com.susanghan_guys.server.user.domain.type.Role;
import com.susanghan_guys.server.user.domain.type.SocialLogin;
import com.susanghan_guys.server.work.domain.Work;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @Column(name = "is_service_agreement")
    private Boolean isServiceAgreement;

    @Column(name = "is_user_info_agreement")
    private Boolean isUserInfoAgreement;

    @Column(name = "is_marketing_agreement")
    private Boolean isMarketingAgreement;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<Role> roles;

    @Column(name = "channel")
    @Enumerated(EnumType.STRING)
    private Channel channel;

    @Column(name = "channel_etc")
    private String channelEtc;

    @Column(name = "purpose")
    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Column(name = "purpose_etc")
    private String purposeEtc;

    @Column(name = "social_login", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialLogin socialLogin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Work> workList = new ArrayList<>();

    @Builder
    public User(
            String name,
            String email,
            String providerId,
            Boolean isServiceAgreement,
            Boolean isUserInfoAgreement,
            Boolean isMarketingAgreement,
            List<Role> roles,
            Channel channel,
            String channelEtc,
            Purpose purpose,
            String purposeEtc,
            SocialLogin socialLogin
    ) {
        this.name = name;
        this.email = email;
        this.providerId = providerId;
        this.isServiceAgreement = isServiceAgreement;
        this.isUserInfoAgreement = isUserInfoAgreement;
        this.isMarketingAgreement = isMarketingAgreement;
        this.roles = roles;
        this.channel = channel;
        this.channelEtc = channelEtc;
        this.purpose = purpose;
        this.purposeEtc = purposeEtc;
        this.socialLogin = socialLogin;
    }

    public void addWork(Work work) {
        workList.add(work);
        work.associateUser(this);
    }

    public void updateUserAgreement(
            Boolean isServiceAgreement,
            Boolean isUserInfoAgreement,
            Boolean isMarketingAgreement
    ) {
        this.isServiceAgreement = isServiceAgreement;
        this.isUserInfoAgreement = isUserInfoAgreement;
        this.isMarketingAgreement = isMarketingAgreement;
    }

    public void updateUserOnboarding(
            List<Role> roles,
            Purpose purpose,
            String purposeEtc,
            Channel channel,
            String channelEtc
    ) {
        this.roles = roles;
        this.purpose = purpose;
        this.purposeEtc = purposeEtc;
        this.channel = channel;
        this.channelEtc = channelEtc;
    }
}
