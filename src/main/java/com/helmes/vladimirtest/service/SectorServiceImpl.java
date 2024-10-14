package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.mapper.SectorMapper;
import com.helmes.vladimirtest.repository.SectorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    public List<SectorEntity> collectSectorsFromIdList(String selectedSectorList) {
        List<String> sectorIdList = new ArrayList<>(Arrays.asList(selectedSectorList.split(",")));
        List<SectorEntity> sectorEntities = new ArrayList<>();
        for (String selectedSector : sectorIdList) {
            SectorEntity sectorEntity = new SectorEntity();
            sectorEntity.setId(Long.valueOf(selectedSector));
            sectorEntities.add(sectorEntity);
        }
        return sectorEntities;
    }

    @Override
    public Model listSectors(Model model) throws Exception {
        try {
            var sectorList = sectorRepository.findAll();
            var parentSectorList = sectorRepository.getParentSectors();

            model.addAttribute("parentSectorList", sectorMapper.toDto(parentSectorList));
            model.addAttribute("sectorList", sectorMapper.toDto(sectorList));

            return model;
        } catch (Exception e) {
            log.error("Failed to list sectors with exception: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
