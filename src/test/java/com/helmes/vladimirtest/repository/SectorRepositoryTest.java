package com.helmes.vladimirtest.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SectorRepositoryTest {

    @Autowired
    private SectorRepository sectorRepository;

    @Test
    void getParentSectors() {
        assertInstanceOf(List.class, sectorRepository.getParentSectors());
        assertNotNull(sectorRepository.getParentSectors());
    }
}