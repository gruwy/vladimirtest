package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.SectorDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.mapper.SectorMapper;
import com.helmes.vladimirtest.repository.SectorRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SectorServiceImpl implements SectorService {
//Convert methods to output DTO's
    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    @Override
    public List<SectorDto> listSectors() {
        var sectors = sectorRepository.findAll();
        return sectorMapper.toDto(sectors);
    }

    @Override
    public List<SectorDto> collectSectorsFromIdList(String selectedSectorIdString) {
        List<String> sectorIdList = new ArrayList<>(Arrays.asList(selectedSectorIdString.split(",")));
        List<SectorEntity> sectorEntities = new ArrayList<>();
        for (String selectedSector : sectorIdList) {
            SectorEntity sectorEntity = new SectorEntity();
            sectorEntity.setId(Long.valueOf(selectedSector));
            sectorEntities.add(sectorEntity);
        }
        var sectorEntityDtos = sectorMapper.toDto(sectorEntities);
        return sectorEntityDtos;
    }



    @Override
    public List<SectorEntity> findMainSectors() {
        return sectorRepository.getSectorEntitiesByParentSectorIdNull();
    }

    @Override
    public List<SectorEntity> findSubSectors() {
        return sectorRepository.getSectorEntitiesByParentSectorIdNotNull();
    }
}
