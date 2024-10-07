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

    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    @Override
    public List<SectorEntity> collectSectorsFromIdList(String selectedSectorIdString) {
        List<String> sectorIdList = new ArrayList<>(Arrays.asList(selectedSectorIdString.split(",")));
        List<SectorEntity> sectorEntities = new ArrayList<>();
        for (String selectedSector : sectorIdList) {
            SectorEntity sectorEntity = new SectorEntity();
            sectorEntity.setId(Long.valueOf(selectedSector));
            sectorEntities.add(sectorEntity);
        }
        return sectorEntities;
    }

    @Override
    public List<SectorDto> getParentSectors() {
        return sectorMapper.toDto(sectorRepository.getParentSectors());
    }
}
