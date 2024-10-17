package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.exception.*;

import java.util.List;

public interface UserService {

    UserDto saveUser(String selectedSectorList, UserDto userDto) throws UserAlreadyExistsException, NoSectorsChosenException, UserServiceLogicException;

    UserDto updateUser(String selectedSectorList, UserDto userDto) throws UserNotFoundException, NoSectorsChosenException, UserServiceLogicException;

    List<Long> getUserSectorIdList(UserDto userDto) throws UserNotFoundException, UserSectorsNotFoundException, UserServiceLogicException;
}
