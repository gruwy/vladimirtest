package com.helmes.vladimirtest.integration.repository;

import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.repository.SectorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SectorRepositoryIntTest {

    @Autowired
    private SectorRepository sectorRepository;

    @Test
    void getParentSectors_ReturnsParentSectors() {
        assertInstanceOf(List.class, sectorRepository.getParentSectors());
        assertNotNull(sectorRepository.getParentSectors());
    }

    @Test
    void getParentSectors_ReturnsNoParentSectors() {
        sectorRepository.deleteAll();
        assertInstanceOf(List.class, sectorRepository.getParentSectors());
        var emptySectorList = new ArrayList<SectorEntity>();
        assertEquals(sectorRepository.getParentSectors(), emptySectorList);
    }
}