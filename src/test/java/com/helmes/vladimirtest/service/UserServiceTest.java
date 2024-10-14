package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.mapper.UserMapper;
import com.helmes.vladimirtest.mapper.UserMapperImpl;
import com.helmes.vladimirtest.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private final UserMapper userMapper = new UserMapperImpl();


    @BeforeEach
    void setUp() {
        UserDto userDto = new UserDto();
        userDto.setUserName("TestUser");
        userDto.setAgreedToTerms(true);
        String selectedSectorList = "1,2,3,4";
        userRepository.save(userMapper.toEntity(userDto));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
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