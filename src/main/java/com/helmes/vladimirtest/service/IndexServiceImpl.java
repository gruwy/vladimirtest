package com.helmes.vladimirtest.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@Configurable
@AllArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final SectorService sectorService;

    @Override
    public Model initIndex(Model model) {
        model.addAttribute("sectorList", sectorService.listSectors());
        return model;
    }

}
