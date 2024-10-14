package com.helmes.vladimirtest.controller;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.service.IndexService;
import com.helmes.vladimirtest.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> index(Model model) throws Exception {
        indexService.initIndex(model);
        return new ResponseEntity<>("index", HttpStatus.OK);
    }

    @GetMapping("/refill")
    public ResponseEntity<String> refillIndex(Model model,
                              @Valid UserDto userDto) throws Exception {
        indexService.refillIndex(model, userDto);
        return new ResponseEntity<>("index", HttpStatus.OK);
    }

    @PostMapping("/execute")
    public ResponseEntity<String> execute (Model model,
                                           @Param(value = "selectedSectorList") String selectedSectorList,
                                           @RequestParam(value="action") String action,
                                           @Valid UserDto userDto) throws Exception {
        switch (action) {
            case addUser -> {
                userService.saveUser(selectedSectorList, userDto);
                return new ResponseEntity<>("\"redirect:/\"", HttpStatus.CREATED);
            }
            case updateUser -> {
                userService.updateUser(selectedSectorList, userDto);
                return new ResponseEntity<>("\"redirect:/\"", HttpStatus.OK);
            }
            case refill -> {
                refillIndex(model, userDto);
                return new ResponseEntity<>("", HttpStatus.OK);
            }
            default -> {
                return new ResponseEntity<>("\"redirect:/\"", HttpStatus.OK);
            }
        }
    }
}
