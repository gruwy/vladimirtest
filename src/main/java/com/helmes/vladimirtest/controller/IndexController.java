package com.helmes.vladimirtest.controller;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.service.IndexService;
import com.helmes.vladimirtest.service.SectorService;
import com.helmes.vladimirtest.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final IndexService indexService;
    private final SectorService sectorService;
    private final UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        indexService.initIndex(model);
        return "index";
    }

    @PostMapping("/execute")
    public String execute (Model model,
                           @Valid UserDto userDto) {
        userService.saveUser(model, userDto);
        return "redirect:/";
    }
}
