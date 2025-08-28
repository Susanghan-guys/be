package com.susanghan_guys.server.personalwork.infrastructure.persistence;

import com.susanghan_guys.server.personalwork.domain.BrandBrief;
import com.susanghan_guys.server.work.domain.type.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandBriefRepository extends JpaRepository<BrandBrief, Long> {
    Optional<BrandBrief> findByBrand(Brand brand);
}