package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;

import org.springframework.ui.Model;

public interface IndexService {

    void initIndex(Model model);

    void refillIndex(Model model, UserDto userDto);
}
