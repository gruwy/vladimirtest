package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final SectorService sectorService;
    private final UserService userService;

    @Override
    public void initIndex(Model model) throws Exception {
        try {
            sectorService.listSectors(model);
            model.addAttribute("userSectorList", "");

        } catch (Exception e) {
            log.error("Failed to initialize index with exception {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void refillIndex(Model model, UserDto userDto) throws Exception {
        try {
            sectorService.listSectors(model);
            model.addAttribute("userSectorList", userService.getUserSectorIdList(userDto));

        } catch (Exception e) {
            log.error("Failed to refill index with exception {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
