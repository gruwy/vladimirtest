package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(String selectedSectorList, UserDto userDto) throws Exception;

    UserDto updateUser(String selectedSectorList, UserDto userDto) throws Exception;

    List<Long> getUserSectorIdList(UserDto userDto) throws Exception;
}
