package com.helmes.vladimirtest.repository;

import com.helmes.vladimirtest.entity.SectorFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorFormRepository extends JpaRepository<SectorFormEntity, Long> {
}
