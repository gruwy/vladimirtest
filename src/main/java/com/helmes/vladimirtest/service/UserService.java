package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional
    UserDto saveUser(UserDto userDto);

}
