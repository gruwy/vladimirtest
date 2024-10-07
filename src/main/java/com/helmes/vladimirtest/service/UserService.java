package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.ApiResponseDto;
import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.exception.UserAlreadyExistsException;
import com.helmes.vladimirtest.exception.UserNotFoundException;
import com.helmes.vladimirtest.exception.UserServiceLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService {

    ResponseEntity<ApiResponseDto<?>> saveUser(Model model, String selectedSectorList, UserDto userDto) throws UserAlreadyExistsException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> updateUser(Model model, String selectedSectorList, UserDto userDto) throws UserAlreadyExistsException, UserNotFoundException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> refillUserSectors(Model model, UserDto userDto) throws UserServiceLogicException;

    List<Long> getUserSectorIdList(UserDto userDto);
}
