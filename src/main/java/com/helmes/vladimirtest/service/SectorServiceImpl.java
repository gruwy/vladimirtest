package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.SectorDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.mapper.SectorMapper;
import com.helmes.vladimirtest.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class SectorServiceImpl implements SectorService {

    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    @Override
    public List<SectorDto> collectSectorsFromIdList(String selectedSectorList) {
        List<String> sectorIdList = new ArrayList<>(Arrays.asList(selectedSectorList.split(",")));
        List<SectorEntity> sectorEntities = new ArrayList<>();
        for (String selectedSector : sectorIdList) {
            SectorEntity sectorEntity = new SectorEntity();
            sectorEntity.setId(Long.valueOf(selectedSector));
            sectorEntities.add(sectorEntity);
        }
        return sectorMapper.toDto(sectorEntities);
    }

    @Override
    public List<SectorDto> listAllSectors() throws Exception {
        try {
            var sectorList = sectorRepository.findAll();
            return sectorMapper.toDto(sectorList);
        } catch (Exception e) {
            log.error("Failed to list sectors with exception: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<SectorDto> listParentSectors() throws Exception {
        try {
            var parentSectorList = sectorRepository.getParentSectors();
            return sectorMapper.toDto(parentSectorList);
        } catch (Exception e) {
            log.error("Failed to list parent sectors with exception: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
