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

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "channel")
    @Enumerated(EnumType.STRING)
    private Channel channel;

    @Column(name = "purpose")
    @Enumerated(EnumType.STRING)
    private Purpose purpose;

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
            Role role,
            Channel channel,
            Purpose purpose,
            SocialLogin socialLogin
    ) {
        this.name = name;
        this.email = email;
        this.providerId = providerId;
        this.role = role;
        this.channel = channel;
        this.purpose = purpose;
        this.socialLogin = socialLogin;
    }

    public void addWork(Work work) {
        workList.add(work);
        work.associateUser(this);
    }
}
