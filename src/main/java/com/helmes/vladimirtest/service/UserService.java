package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.ApiResponseDto;
import com.helmes.vladimirtest.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<ApiResponseDto<?>> saveUser(String selectedSectorList, UserDto userDto) throws Exception;

    ResponseEntity<ApiResponseDto<?>> updateUser(String selectedSectorList, UserDto userDto) throws Exception;

    List<Long> getUserSectorIdList(UserDto userDto) throws Exception;
}
