package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.SectorDto;
import com.helmes.vladimirtest.entity.SectorEntity;

import java.util.List;

public interface SectorService {

    List<SectorDto> listSectors();

    List<SectorEntity> collectSectorsFromIdList(String selectedSectorIdString);

    List<SectorDto> getParentSectors();
}
