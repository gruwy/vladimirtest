package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.ApiResponseDto;
import com.helmes.vladimirtest.dto.ApiResponseStatus;
import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.entity.UserEntity;
import com.helmes.vladimirtest.exception.UserAlreadyExistsException;
import com.helmes.vladimirtest.exception.UserNotFoundException;
import com.helmes.vladimirtest.exception.UserServiceLogicException;
import com.helmes.vladimirtest.mapper.UserMapper;
import com.helmes.vladimirtest.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SectorService sectorService;

    @Transactional
    @Override
    public ResponseEntity<ApiResponseDto<?>> saveUser(Model model, String selectedSectorList, UserDto userDto) throws UserAlreadyExistsException, UserServiceLogicException {
        try {
            var user = userRepository.findByUserName(userDto.getUserName());
            if (user != null){
                throw new UserAlreadyExistsException("Registration failed: User already exists with username " + userDto.getUserName());
            } else user = new UserEntity();
            var userSectorList = sectorService.collectSectorsFromIdList(selectedSectorList);
            user.setUserName(userDto.getUserName());
            user.setSectors(userSectorList);
            user.setAgreedToTerms(userDto.getAgreedToTerms());
            userRepository.save(user);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "New user has been successfully created!"));
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to create new user account with exception: {}", e.getMessage());
            throw new UserServiceLogicException();
        }

    }

    @Transactional
    @Override
    public ResponseEntity<ApiResponseDto<?>> updateUser(Model model, String selectedSectorList, UserDto userDto) throws UserNotFoundException, UserServiceLogicException {
        try {
            Optional<UserEntity> user = Optional.of(userRepository.findByUserName(userDto.getUserName()));
            user.map(
                    userEntity -> {
                        userEntity.setSectors(sectorService.collectSectorsFromIdList(selectedSectorList));
                        model.addAttribute("userDto", userMapper.toDto(userRepository.save(userEntity)));
                        return userEntity;
                    }
            ).orElseThrow(() -> new UserNotFoundException("User not found with name " + userDto.getUserName()));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User updated successfully!")
                    );
        } catch(UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch(Exception e) {
            log.error("Failed to update user {} with exception {}", userDto.getUserName(), e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> refillUserSectors(Model model, UserDto userDto) throws UserServiceLogicException {
        try {
            var userSectors = userRepository.findByUserName(userDto.getUserName()).getSectors();
            userDto.setSectors(userSectors);
            model.addAttribute("userDto", userDto);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User sectors refilled successfully!")
                    );
        } catch (Exception e) {
            log.error("Failed to refill user sectors for user {} with exception {}", userDto.getUserName(), e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public List<Long> getUserSectorIdList(UserDto userDto) {
        var userSectors = userRepository.findByUserName(userDto.getUserName()).getSectors();
        List<Long> userSectorIdList = new ArrayList<>();
        for (SectorEntity sectorEntity : userSectors) {
            var sectorId = sectorEntity.getId();
            userSectorIdList.add(sectorId);
        }
        return userSectorIdList;
    }

}
