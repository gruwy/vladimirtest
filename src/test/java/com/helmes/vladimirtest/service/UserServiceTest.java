package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.exception.NoSectorsChosenException;
import com.helmes.vladimirtest.exception.UserAlreadyExistsException;
import com.helmes.vladimirtest.mapper.SectorMapperImpl;
import com.helmes.vladimirtest.mapper.UserMapperImpl;
import com.helmes.vladimirtest.repository.SectorRepository;
import com.helmes.vladimirtest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Autowired
    private SectorMapperImpl sectorMapper;

    @Autowired
    private UserMapperImpl userMapper;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUser_whenSectorListEmptyThrowException() throws Exception {
        var user = prepareUser();
        String selectedSectorIds = "";

        Throwable exception = assertThrows(NoSectorsChosenException.class, () -> userService.saveUser(selectedSectorIds, user));
        assertEquals("Failed to create user with exception: No sectors chosen by the user", exception.getMessage());
    }

    @Test
    void saveUser_whenUserExistsThrowException() throws Exception {
        var user = prepareUser();
        userRepository.save(userMapper.toEntity(user));
        String selectedSectorIds = "1,2,3,4";

        Throwable exception = assertThrows(UserAlreadyExistsException.class, () -> userService.saveUser(selectedSectorIds, user));
        assertEquals("Failed to create user with exception: User already exists with name " + user.getUserName(), exception.getMessage());
    }

    @Test
    void saveUser_whenUserDoesntExist() throws Exception {
        var user = prepareUser();

        String selectedSectorIds = "1,2,3,4";
        var savedUser = userService.saveUser(selectedSectorIds, user);

        String referenceSectorList = user.getSectors().toString();
        String savedSectorList = savedUser.getSectors().toString();

        assertNotNull(savedUser);
        assertEquals(user.getUserName(), savedUser.getUserName());
        assertEquals(user.getSectors().size(), savedUser.getSectors().size());
        assertEquals(referenceSectorList, savedSectorList);
        assertEquals(user.getAgreedToTerms(), savedUser.getAgreedToTerms());
    }

    @Test
    void updateUser() throws Exception {
        var user = prepareUser();
        userRepository.save(userMapper.toEntity(user));

        String newSectorList = "5,6,7,8";
        try {
            UserDto updatedUser = userService.updateUser(newSectorList, user);

            String referenceSectorList = user.getSectors().toString();
            String updatedSectorList = updatedUser.getSectors().toString();

            assertNotNull(updatedUser);
            assertEquals(user.getUserName(), updatedUser.getUserName());
            assertNotEquals(referenceSectorList, updatedSectorList);
            assertThat(updatedSectorList, containsString("id=5"));
            assertThat(updatedSectorList, containsString("id=6"));
            assertThat(updatedSectorList, containsString("id=7"));
            assertThat(updatedSectorList, containsString("id=8"));
            assertEquals(user.getAgreedToTerms(), updatedUser.getAgreedToTerms());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
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

    private UserDto prepareUser() throws Exception {
        UserDto userDto = new UserDto();
        String sectorString = "1,2,3,4";
        List<String> sectorSectorIdList = new ArrayList<>(Arrays.asList(sectorString.split(",")));
        var sectorList = sectorRepository.getSectorsByIdList(sectorSectorIdList);
        userDto.setUserName("TestUser");
        userDto.setAgreedToTerms(true);
        userDto.setSectors(sectorList);
        return userDto;
    }

}