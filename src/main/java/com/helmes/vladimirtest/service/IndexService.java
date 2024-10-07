package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.ApiResponseDto;
import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.exception.IndexInitException;
import com.helmes.vladimirtest.exception.IndexRefillException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

public interface IndexService {

    ResponseEntity<ApiResponseDto<?>> initIndex(Model model) throws IndexInitException;

    ResponseEntity<ApiResponseDto<?>> refillIndex(Model model, UserDto userDto) throws IndexRefillException;
}
