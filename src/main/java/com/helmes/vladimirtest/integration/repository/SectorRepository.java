package com.helmes.vladimirtest.integration.repository;

import com.helmes.vladimirtest.entity.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<SectorEntity, Long> {
    @Query("SELECT s FROM sector s JOIN sector ps ON s.id = ps.parentSector.id")
    List<SectorEntity> getParentSectors();

    @Query("SELECT s FROM sector s WHERE s.id IN (:selectedSectorList)")
    List<SectorEntity> getSectorsByIdList(List<String> selectedSectorList);

}
