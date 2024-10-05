package com.helmes.vladimirtest.repository;

import com.helmes.vladimirtest.entity.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<SectorEntity, Long> {
    List<SectorEntity> getSectorEntitiesByParentSectorIdNull();
    List<SectorEntity> getSectorEntitiesByParentSectorIdNotNull();
}
