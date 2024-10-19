package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.SectorDto;
import com.helmes.vladimirtest.exception.SectorServiceLogicException;

import java.util.List;

public interface SectorService {

    List<SectorDto> listAllSectors() throws SectorServiceLogicException;

    List<SectorDto> listParentSectors() throws SectorServiceLogicException;
}
