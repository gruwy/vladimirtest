package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.ApiResponseDto;
import com.helmes.vladimirtest.dto.ApiResponseStatus;
import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.exception.IndexServiceLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@Slf4j
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final SectorService sectorService;
    private final UserService userService;

    @Override
    public ResponseEntity<ApiResponseDto<?>> initIndex(Model model) throws IndexServiceLogicException {
        try {
            model.addAttribute("sectorList", sectorService.listSectors());
            model.addAttribute("parentSectorList", sectorService.getParentSectors());
            model.addAttribute("userSectorList", "");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Index initialized successfully!")
                    );
        } catch (Exception e) {
            log.error("Failed to initialize index with exception {}", e.getMessage());
            throw new IndexServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> refillIndex(Model model, UserDto userDto) throws IndexServiceLogicException {
        try {
            model.addAttribute("sectorList", sectorService.listSectors());
            model.addAttribute("parentSectorList", sectorService.getParentSectors());
            model.addAttribute("userSectorList", userService.getUserSectorIdList(userDto));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Index refilled successfully!")
                    );
        } catch (Exception e) {
            log.error("Failed to refill index with exception {}", e.getMessage());
            throw new IndexServiceLogicException();
        }

    }

}
