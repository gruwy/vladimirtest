package com.helmes.vladimirtest.controller;

import com.helmes.vladimirtest.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final SectorService sectorService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("sectorList", sectorService.listSectors());
        return "index";
    }
}
