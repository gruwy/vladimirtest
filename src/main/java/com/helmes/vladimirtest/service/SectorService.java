package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.SectorDto;
import com.helmes.vladimirtest.entity.SectorEntity;

import java.util.List;

public interface SectorService {
    List<SectorDto> listSectors();

    List<SectorDto> collectSectorsFromIdList(String selectedSectorIdString);

    List<SectorEntity> findMainSectors();

    List<SectorEntity> findSubSectors();
}
