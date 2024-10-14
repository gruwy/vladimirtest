package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.entity.SectorEntity;
import org.springframework.ui.Model;

import java.util.List;

public interface SectorService {

    List<SectorEntity> collectSectorsFromIdList(String selectedSectorList);

    void listSectors(Model model) throws Exception;
}
