package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto saveUser(String selectedSectorList, UserDto userDto) throws Exception;

    Optional<UserDto> updateUser(String selectedSectorList, UserDto userDto) throws Exception;

    List<Long> getUserSectorIdList(UserDto userDto) throws Exception;
}
