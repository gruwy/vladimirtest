package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.mapper.UserMapper;
import com.helmes.vladimirtest.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    private UserDto userDto;

    private String selectedSectorList;

    private UserMapper userMapper;

    private Model model;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserName("TestUser");
        userDto.setAgreedToTerms(true);
        selectedSectorList = "1,2,3,4";
        userRepository.save(userMapper.toEntity(userDto));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void saveUser_shouldReturnUserNotFoundException() throws Exception {
        var user = userRepository.findByUserName("FailUser");
    }

    @Test
    void updateUser() {
    }

    @Test
    void refillUserSectors() {
    }

    @Test
    void getUserSectorIdList() {
    }
}