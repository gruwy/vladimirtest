package com.helmes.vladimirtest.controller;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.exception.*;
import com.helmes.vladimirtest.service.SectorService;
import com.helmes.vladimirtest.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final UserService userService;
    private final SectorService sectorService;

    private final String addUser = "Add New User & Save Form";
    private final String updateUser = "Update Form Choices For Existing User";
    private final String refill = "Refill Form";
    private final String index = "index";
    private final String redirect = "redirect:/";

    @GetMapping("/")
    public String index(Model model)
            throws PrepareModelException {

        prepareModel(model);
        model.addAttribute("userSectorList", "");

        return index;
    }

    @GetMapping("/refill")
    public String refillIndex(Model model,
                              @Valid UserDto userDto)
            throws PrepareModelException, UserNotFoundException, UserServiceLogicException, UserSectorsNotFoundException {

        prepareModel(model);
        var userSectorIdList = userService.getUserSectorIdList(userDto);
        model.addAttribute("userSectorList", userSectorIdList);

        return index;
    }

    private Model prepareModel(Model model)
            throws PrepareModelException {
        try {
            var parentSectorList = sectorService.listParentSectors();
            var allSectors = sectorService.listAllSectors();

            model.addAttribute("parentSectorList", parentSectorList);
            model.addAttribute("sectorList", allSectors);

            return model;

        } catch (Exception e) {
            log.error("Failed to prepare sector data model with exception {}", e.getMessage());
            throw new PrepareModelException();
        }

    }

    @PostMapping("/execute")
    public String execute (Model model,
                           @Param(value = "selectedSectorList") String selectedSectorList,
                           @RequestParam(value="action") String action,
                           @Valid @ModelAttribute("userDto") UserDto userDto)
            throws NoSectorsChosenException, UserAlreadyExistsException, UserServiceLogicException,
                   UserNotFoundException, PrepareModelException, UserSectorsNotFoundException {

        switch (action) {
            case addUser -> {
                userService.saveUser(selectedSectorList, userDto);
                return redirect;
            }
            case updateUser -> {
                userService.updateUser(selectedSectorList, userDto);
                return redirect;
            }
            case refill -> {
                refillIndex(model, userDto);
                return index;
            }
            default -> {
                return redirect;
            }
        }
    }
}
