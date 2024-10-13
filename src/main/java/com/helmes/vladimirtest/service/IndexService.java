package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.ApiResponseDto;
import com.helmes.vladimirtest.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

public interface IndexService {

    ResponseEntity<ApiResponseDto<?>> initIndex(Model model) throws Exception;

    ResponseEntity<ApiResponseDto<?>> refillIndex(Model model, UserDto userDto) throws Exception;
}
