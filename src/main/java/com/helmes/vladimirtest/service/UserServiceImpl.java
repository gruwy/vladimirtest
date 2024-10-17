package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.entity.UserEntity;
import com.helmes.vladimirtest.exception.*;
import com.helmes.vladimirtest.mapper.UserMapper;
import com.helmes.vladimirtest.repository.SectorRepository;
import com.helmes.vladimirtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SectorRepository sectorRepository;

    @Override
    public UserDto saveUser(String selectedSectorIds, UserDto userDto) throws UserAlreadyExistsException, NoSectorsChosenException, UserServiceLogicException {
        try {

            var user = userRepository.findByUserName(userDto.getUserName());
            if (user != null) {
                throw new UserAlreadyExistsException("Failed to create user with exception: User already exists with name " + userDto.getUserName());
            } else user = new UserEntity();

            if (selectedSectorIds.isEmpty()) {
                throw new NoSectorsChosenException("Failed to create user with exception: No sectors chosen by the user");
            }

            List<String> sectorSectorIdList = new ArrayList<>(Arrays.asList(selectedSectorIds.split(",")));
            var userSectorList = sectorRepository.getSectorsByIdList(sectorSectorIdList);
            user.setUserName(userDto.getUserName());
            user.setSectors(userSectorList);
            user.setAgreedToTerms(userDto.getAgreedToTerms());
            var savedUser = userRepository.save(user);
            var dto = userMapper.toDto(savedUser);

            return dto;

        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        } catch (NoSectorsChosenException e) {
            throw new NoSectorsChosenException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to create user with exception: {}", e.getMessage());
            throw new UserServiceLogicException();
        }

    }

    @Override
    public UserDto updateUser(String selectedSectorIds, UserDto userDto) throws UserNotFoundException, NoSectorsChosenException, UserServiceLogicException {
        try {
            var user = userRepository.findByUserName(userDto.getUserName());
            if (user == null) {
                throw new UserNotFoundException("Failed to update user with exception: User not found with name " + userDto.getUserName());
            }

            if (selectedSectorIds.isEmpty()) {
                throw new NoSectorsChosenException("Failed to update user with exception: No sectors chosen by the user");
            }

            List<String> sectorSectorIdList = new ArrayList<>(Arrays.asList(selectedSectorIds.split(",")));
            var userSectorList = sectorRepository.getSectorsByIdList(sectorSectorIdList);
            user.setSectors(userSectorList);

            var savedUser = userRepository.save(user);
            var dto = userMapper.toDto(savedUser);

            return dto;

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (NoSectorsChosenException e) {
            throw new NoSectorsChosenException(e.getMessage());
        } catch(Exception e) {
            log.error("Failed to update user {} with exception {}", userDto.getUserName(), e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public List<Long> getUserSectorIdList(UserDto userDto) throws UserNotFoundException, SectorsNotFoundForUserException, UserServiceLogicException {
        try {
            var user = userRepository.findByUserName(userDto.getUserName());
            if (user == null) {
                throw new UserNotFoundException("Failed to get user sector list with exception: User not found with name " + userDto.getUserName());
            }
            var userSectors = user.getSectors();
            if (userSectors.isEmpty()) {
                throw new SectorsNotFoundForUserException("Failed to get user sector list with exception: Sector list not found for user {}" + userDto.getUserName());
            }

            var userSectorIdList = userSectors.stream().map(SectorEntity::getId).collect(Collectors.toList());

            return userSectorIdList;
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (SectorsNotFoundForUserException e) {
            throw new SectorsNotFoundForUserException(e.getMessage());
        } catch(Exception e) {
            log.error("Failed to get sector id list for user {} with exception {}", userDto.getUserName(), e.getMessage());
            throw new UserServiceLogicException();
        }
    }
}
