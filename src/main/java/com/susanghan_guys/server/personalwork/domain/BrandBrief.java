package com.susanghan_guys.server.personalwork.domain;

import com.susanghan_guys.server.work.domain.type.Brand;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "brand_brief", uniqueConstraints = @UniqueConstraint(columnNames = "brand"))
public class BrandBrief {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", nullable = false)
    private Brand brand;

    @Column(name = "brand_intro", nullable = false, columnDefinition = "TEXT")
    private String brandIntro;

    @Column(name = "market_status", nullable = false, columnDefinition = "TEXT")
    private String marketStatus;

    @Column(name = "brand_comm_target", nullable = false, columnDefinition = "TEXT")
    private String brandCommTarget;

    @Column(name = "challenge", nullable = false, columnDefinition = "TEXT")
    private String challenge;

    @Column(name = "cautions", nullable = false, columnDefinition = "TEXT")
    private String cautions;

}
