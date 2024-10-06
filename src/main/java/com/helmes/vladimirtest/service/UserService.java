package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.ApiResponseDto;
import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.entity.UserEntity;

import com.helmes.vladimirtest.exception.UserAlreadyExistsException;
import com.helmes.vladimirtest.exception.UserNotFoundException;
import com.helmes.vladimirtest.exception.UserServiceLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface UserService {

    ResponseEntity<ApiResponseDto<?>> saveUser(Model model, String selectedSectorIdString, UserDto userDto) throws UserAlreadyExistsException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> updateUser(Model model, String selectedSectorIdString, UserDto userDto) throws UserAlreadyExistsException, UserNotFoundException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> refillUserSectors(Model model, UserDto userDto) throws UserServiceLogicException;

    List<Long> getUserSectorIdList(UserDto userDto);

    List<SectorEntity> getUserSectors(UserDto userDto);

    Optional<UserEntity> getUserEntityByUserName(UserDto userDto);
}
