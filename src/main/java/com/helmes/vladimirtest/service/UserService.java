package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import org.springframework.ui.Model;

public interface UserService {

    UserDto saveUser(Model model, UserDto userDto);
}
