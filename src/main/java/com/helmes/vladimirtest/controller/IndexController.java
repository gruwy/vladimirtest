package com.helmes.vladimirtest.controller;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.exception.IndexServiceLogicException;
import com.helmes.vladimirtest.exception.UserAlreadyExistsException;
import com.helmes.vladimirtest.exception.UserNotFoundException;
import com.helmes.vladimirtest.exception.UserServiceLogicException;
import com.helmes.vladimirtest.service.IndexService;
import com.helmes.vladimirtest.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final IndexService indexService;
    private final UserService userService;

    private final String addUser = "Add New User & Save Form";
    private final String updateUser = "Update Form Choices For Existing User";
    private final String refill = "Refill Form";

    @GetMapping("/")
    public String index(Model model) throws IndexServiceLogicException {
        indexService.initIndex(model);
        return "index";
    }

    @GetMapping("/refill")
    public String refillIndex(Model model,
                              @Valid UserDto userDto) throws IndexServiceLogicException {
        indexService.refillIndex(model, userDto);
        return "index";
    }

    @PostMapping("/execute")
    public String execute (Model model,
                           @Param(value = "selectedSectorIdString") String selectedSectorIdString,
                           @RequestParam(value="action") String action,
                           @Valid UserDto userDto) throws UserAlreadyExistsException, UserServiceLogicException, UserNotFoundException, IndexServiceLogicException {
        switch (action) {
            case addUser -> {
                userService.saveUser(model, selectedSectorIdString, userDto);
                return "redirect:/";
            }
            case updateUser -> {
                userService.updateUser(model, selectedSectorIdString, userDto);
                return "redirect:/";
            }
            case refill -> {
                userService.refillUserSectors(model, userDto);
                return refillIndex(model, userDto);
            }
            default -> {
                return "redirect:/";
            }
        }
    }
}
