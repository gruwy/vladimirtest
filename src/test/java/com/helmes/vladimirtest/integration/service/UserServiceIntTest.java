package com.helmes.vladimirtest.integration.service;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.exception.NoSectorsChosenException;
import com.helmes.vladimirtest.exception.UserAlreadyExistsException;
import com.helmes.vladimirtest.exception.UserNotFoundException;
import com.helmes.vladimirtest.exception.UserSectorsNotFoundException;
import com.helmes.vladimirtest.mapper.UserMapperImpl;
import com.helmes.vladimirtest.integration.repository.SectorRepository;
import com.helmes.vladimirtest.integration.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceIntTest {

    @Autowired
    private UserMapperImpl userMapper;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @AfterEach
    void breakDown() {
        userRepository.deleteAll();
    }

    @Test
    void saveUser_whenSectorListEmptyThrowException() {
        var user = prepareUser();
        var emptySectorList = new ArrayList<SectorEntity>();
        user.setSectors(emptySectorList);
        String selectedSectorIds = "";

        Throwable exception = assertThrows(NoSectorsChosenException.class, () -> userService.saveUser(selectedSectorIds, user));
        assertEquals("Failed to create user with exception: No sectors chosen by the user", exception.getMessage());
    }

    @Test
    void saveUser_whenUserExistsThrowException() {
        var user = prepareUser();
        var emptySectorList = new ArrayList<SectorEntity>();
        user.setSectors(emptySectorList);
        var savedUser = userRepository.save(userMapper.toEntity(user));
        String selectedSectorIds = "1,2,3,4";

        Throwable exception = assertThrows(UserAlreadyExistsException.class, () -> userService.saveUser(selectedSectorIds, userMapper.toDto(savedUser)));
        assertEquals("Failed to create user with exception: User already exists with name " + user.getUserName(), exception.getMessage());
    }

    @Test
    void saveUser() throws Exception {
        var referenceUser = prepareUser();

        var emptySectorList = new ArrayList<SectorEntity>();
        UserDto toSaveUser = new UserDto();
        toSaveUser.setUserName(referenceUser.getUserName());
        toSaveUser.setSectors(emptySectorList);
        toSaveUser.setAgreedToTerms(referenceUser.getAgreedToTerms());

        String selectedSectorIds = "1,2,3,4";
        var savedUser = userService.saveUser(selectedSectorIds, toSaveUser);

        String referenceSectorList = referenceUser.getSectors().toString();
        String savedSectorList = savedUser.getSectors().toString();

        assertNotNull(savedUser);
        assertEquals(referenceUser.getUserName(), savedUser.getUserName());
        assertEquals(referenceUser.getSectors().size(), savedUser.getSectors().size());
        assertEquals(referenceSectorList, savedSectorList);
        assertEquals(referenceUser.getAgreedToTerms(), savedUser.getAgreedToTerms());
    }

    @Test
    void updateUser_whenUserNotFound() {
        var user = prepareUser();
        String newSectorList = "5,6,7,8";

        Throwable exception = assertThrows(UserNotFoundException.class, () -> userService.updateUser(newSectorList, user));
        assertEquals("Failed to update user with exception: User not found with name " + user.getUserName(), exception.getMessage());
    }

    @Test
    void updateUser_whenSectorsNotSelected() {
        var user = prepareUser();
        var savedUser = userRepository.save(userMapper.toEntity(user));
        String newSectorList = "";

        Throwable exception = assertThrows(NoSectorsChosenException.class, () -> userService.updateUser(newSectorList, userMapper.toDto(savedUser)));
        assertEquals("Failed to update user with exception: No sectors chosen by the user", exception.getMessage());
    }

    @Test
    void updateUser() throws Exception {
        var user = prepareUser();
        var savedUser = userRepository.save(userMapper.toEntity(user));

        String newSectorList = "5,6,7,8";

        UserDto updatedUser = userService.updateUser(newSectorList, userMapper.toDto(savedUser));

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

    @Test
    void getUserSectorIdList_whenUserNotFound() {
        var user = prepareUser();

        Throwable exception = assertThrows(UserNotFoundException.class, () -> userService.getUserSectorIdList(user));
        assertEquals("Failed to get user sector list with exception: User not found with name " + user.getUserName(), exception.getMessage());
    }

    @Test
    void getUserSectorIdList_whenUserSectorsNotFound() {
        var user = prepareUser();
        var emptySectorList = new ArrayList<SectorEntity>();
        user.setSectors(emptySectorList);
        var savedUser = userRepository.save(userMapper.toEntity(user));

        Throwable exception = assertThrows(UserSectorsNotFoundException.class, () -> userService.getUserSectorIdList(userMapper.toDto(savedUser)));
        assertEquals("Failed to get user sector list with exception: Sector list not found for user " + user.getUserName(), exception.getMessage());
    }

    @Test
    void getUserSectorIdList() {
        var user = prepareUser();
        var savedUser = userRepository.save(userMapper.toEntity(user));

        var userSectorIdList = savedUser.getSectors().stream().map(SectorEntity::getId).toList();
        var listString = userSectorIdList.toString();

        assertThat(listString, containsString("1"));
        assertThat(listString, containsString("2"));
        assertThat(listString, containsString("3"));
        assertThat(listString, containsString("4"));
    }


    private UserDto prepareUser() {
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