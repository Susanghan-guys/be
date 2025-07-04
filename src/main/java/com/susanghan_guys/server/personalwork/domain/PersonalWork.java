package com.susanghan_guys.server.personalwork.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.personalwork.domain.type.ItemType;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    private ItemType itemType;

    private String title;

    private String subTitle;

    private String content;

    private Integer score;


}
