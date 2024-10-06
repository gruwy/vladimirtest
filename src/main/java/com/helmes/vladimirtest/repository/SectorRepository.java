package com.helmes.vladimirtest.repository;

import com.helmes.vladimirtest.entity.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<SectorEntity, Long> {

    @Query("SELECT s from sector s where s.id in (select distinct s.parentSector.id from sector s)")
    List<SectorEntity> getParentSectors();

}
