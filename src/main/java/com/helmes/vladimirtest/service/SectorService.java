package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.SectorDto;

import java.util.List;

public interface SectorService {

    List<SectorDto> listAllSectors() throws Exception;

    List<SectorDto> listParentSectors() throws Exception;
}
