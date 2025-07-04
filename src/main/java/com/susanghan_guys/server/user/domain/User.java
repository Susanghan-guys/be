package com.susanghan_guys.server.user.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.user.domain.type.Channel;
import com.susanghan_guys.server.user.domain.type.Role;
import com.susanghan_guys.server.user.domain.type.SocialLogin;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private Integer age;

    private Role Role;

    private Channel channel;

    private SocialLogin socialLogin;
}
