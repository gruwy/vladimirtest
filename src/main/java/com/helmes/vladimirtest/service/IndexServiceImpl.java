package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final SectorService sectorService;
    private final UserService userService;

    @Override
    public void initIndex(Model model) {
        model.addAttribute("sectorList", sectorService.listSectors());
        model.addAttribute("parentSectorList", sectorService.getParentSectors());
        model.addAttribute("userSectorList", "");
    }

    @Override
    public void refillIndex(Model model, UserDto userDto) {
        model.addAttribute("sectorList", sectorService.listSectors());
        model.addAttribute("parentSectorList", sectorService.getParentSectors());
        model.addAttribute("userSectorList", userService.getUserSectorIdList(userDto));
    }

}
