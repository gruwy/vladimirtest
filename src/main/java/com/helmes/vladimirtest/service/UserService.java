package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.ApiResponseDto;
import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService {

    ResponseEntity<ApiResponseDto<?>> saveUser(Model model, String selectedSectorList, UserDto userDto) throws UserAlreadyExistsException, UserServiceLogicException, NoSectorsChosenException;

    ResponseEntity<ApiResponseDto<?>> updateUser(Model model, String selectedSectorList, UserDto userDto) throws UserAlreadyExistsException, UserNotFoundException, UserServiceLogicException, NoSectorsChosenException;

    ResponseEntity<ApiResponseDto<?>> refillUserSectors(Model model, UserDto userDto) throws UserNotFoundException, UserServiceLogicException;

    List<Long> getUserSectorIdList(UserDto userDto) throws UserNotFoundException, UserServiceLogicException, UserSectorListNotFoundException;
}
