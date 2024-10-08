package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.ApiResponseDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.exception.SectorDataDoesntExistException;
import com.helmes.vladimirtest.exception.SectorServiceLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.List;

public interface SectorService {

    List<SectorEntity> collectSectorsFromIdList(String selectedSectorList);

    ResponseEntity<ApiResponseDto<?>> listSectors(Model model) throws SectorDataDoesntExistException, SectorServiceLogicException;
}
