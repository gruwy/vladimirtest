package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.entity.UserEntity;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto saveUser(Model model, String selectedSectorIdString, UserDto userDto);

    Optional<UserDto> updateUser(Model model, String selectedSectorIdString, UserDto userDto);

    Model refillUserSectors(Model model, UserDto userDto);

    List<Long> getUserSectorIdList(UserDto userDto);

    List<SectorEntity> getUserSectors(UserDto userDto);

    Optional<UserEntity> getUserEntityByUserName(UserDto userDto);
}
