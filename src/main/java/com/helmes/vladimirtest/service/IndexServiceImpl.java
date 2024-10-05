package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@Configurable
@AllArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final SectorService sectorService;
    private final UserService userService;

    @Override
    public void initIndex(Model model) {
        model.addAttribute("sectorList", sectorService.listSectors());
        model.addAttribute("userSectorList", "");
    }

    @Override
    public void refillIndex(Model model, UserDto userDto) {
        model.addAttribute("sectorList", sectorService.listSectors());
        model.addAttribute("userSectorList", userService.getUserSectorIdList(userDto));
    }

}
