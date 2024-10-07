package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.SectorDto;
import com.helmes.vladimirtest.entity.SectorEntity;

import java.util.List;

public interface SectorService {

    List<SectorEntity> collectSectorsFromIdList(String selectedSectorList);

    List<SectorDto> getParentSectors();
}
