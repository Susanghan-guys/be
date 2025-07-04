package com.susanghan_guys.server.work.domain;

import com.susanghan_guys.server.global.domain.BaseEntity;
import com.susanghan_guys.server.work.domain.type.Brand;
import com.susanghan_guys.server.work.domain.type.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Work extends BaseEntity {

    @Id
    @Column(name = "work_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String number;

    private String member;

    private Brand brand;

    private Category category;
}
