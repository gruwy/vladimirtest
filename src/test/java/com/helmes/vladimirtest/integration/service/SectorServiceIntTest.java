package com.helmes.vladimirtest.integration.service;

import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.exception.SectorServiceLogicException;
import com.helmes.vladimirtest.mapper.SectorMapper;
import com.helmes.vladimirtest.integration.repository.SectorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class SectorServiceIntTest {

    @Autowired
    private SectorMapper sectorMapper;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private SectorService sectorService;

    @DirtiesContext
    @Test
    void listAllSectors_noSectorData() throws SectorServiceLogicException {
        sectorRepository.deleteAll();

        String emptyList = new ArrayList<SectorEntity>().toString();

        String serviceSectorList = sectorMapper.toEntity(sectorService.listAllSectors()).toString();

        assertEquals(emptyList, serviceSectorList);
    }

    @Test
    void listAllSectors() throws SectorServiceLogicException {
        String emptyList = new ArrayList<SectorEntity>().toString();

        String referenceSectorList = sectorRepository.findAll().toString();
        String serviceSectorList = sectorMapper.toEntity(sectorService.listAllSectors()).toString();

        assertNotEquals(emptyList, serviceSectorList);
        assertEquals(referenceSectorList, serviceSectorList);
    }

    @DirtiesContext
    @Test
    void listParentSectors_noSectorData() throws SectorServiceLogicException {
        sectorRepository.deleteAll();

        String emptyList = new ArrayList<SectorEntity>().toString();
        String serviceParentSectorList = sectorMapper.toEntity(sectorService.listParentSectors()).toString();

        assertEquals(emptyList, serviceParentSectorList);
    }

    @Test
    void listParentSectors() throws SectorServiceLogicException {
        String emptyList = new ArrayList<SectorEntity>().toString();

        String referenceParentSectorList = sectorRepository.getParentSectors().toString();
        String serviceParentSectorList = sectorMapper.toEntity(sectorService.listParentSectors()).toString();

        assertNotEquals(emptyList, serviceParentSectorList);
        assertEquals(referenceParentSectorList, serviceParentSectorList);
    }

}