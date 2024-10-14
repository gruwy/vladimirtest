package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.entity.SectorEntity;
import org.springframework.ui.Model;

import java.util.List;

public interface SectorService {

    List<SectorEntity> collectSectorsFromIdList(String selectedSectorList);

    Model listSectors(Model model) throws Exception;
}
