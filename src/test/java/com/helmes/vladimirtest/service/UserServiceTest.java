package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.repository.SectorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private SectorEntity sectorEntity;

    @Mock
    private SectorRepository sectorRepository;

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUser() throws Exception {
        String sectorString = "1,2,3,4";
        List<String> sectorSectorIdList = new ArrayList<>(Arrays.asList(sectorString.split(",")));

        UserDto userDto = new UserDto();
        userDto.setUserName("TestUser");
        //userDto.setSectors(sectorRepository.getSectorsByIdList(sectorSectorIdList));
        userDto.setAgreedToTerms(true);

        UserDto savedUser = userService.saveUser(sectorString, userDto);

        Assertions.assertNotNull(savedUser);
        assertEquals(userDto.getUserName(), savedUser.getUserName());
        //Assertions.assertEquals(userDto.getSectors(), savedUser.getSectors());
        assertEquals(userDto.getAgreedToTerms(), savedUser.getAgreedToTerms());
    }

    @Test
    void updateUser() throws Exception {
        String selectedSectorList = "1,2,3,4";
        List<String> sectorSectorIdList = new ArrayList<>(Arrays.asList(selectedSectorList.split(",")));

        UserDto userDto = new UserDto();
        userDto.setUserName("TestUser");
        //userDto.setSectors(sectorRepository.getSectorsByIdList(sectorSectorIdList));
        userDto.setAgreedToTerms(true);

        UserDto savedUser = userService.saveUser(selectedSectorList, userDto);

        String newSectorList = "5,6,7,8";
        UserDto updatedUser = userService.updateUser(newSectorList, savedUser);

        Assertions.assertNotNull(updatedUser);
        assertEquals(savedUser.getUserName(), updatedUser.getUserName());
        //Assertions.assertEquals(savedUser.getSectors(), updatedUser.getSectors());
        assertEquals(savedUser.getAgreedToTerms(), updatedUser.getAgreedToTerms());
    }


    @Test
    void getUserSectorIdList() throws Exception {
        String selectedSectorList = "1,2,3,4";
        List<String> sectorSectorIdList = new ArrayList<>(Arrays.asList(selectedSectorList.split(",")));

        UserDto userDto = new UserDto();
        userDto.setUserName("TestUser");
        //userDto.setSectors(sectorRepository.getSectorsByIdList(sectorSectorIdList));
        userDto.setAgreedToTerms(true);

        UserDto savedUser = userService.saveUser(selectedSectorList, userDto);

    }

}