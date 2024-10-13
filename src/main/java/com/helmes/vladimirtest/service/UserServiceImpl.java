package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.ApiResponseDto;
import com.helmes.vladimirtest.dto.ApiResponseStatus;
import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.entity.UserEntity;
import com.helmes.vladimirtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SectorService sectorService;

    @Override
    public ResponseEntity<ApiResponseDto<?>> saveUser(String selectedSectorList, UserDto userDto) throws Exception {
        try {
            if (selectedSectorList == null) {
                throw new Exception("Failed to create user with exception: No sectors chosen by the user");
            }

            var user = userRepository.findByUserName(userDto.getUserName());
            if (user != null) {
                throw new Exception("Failed to create user with exception: User already exists with name " + userDto.getUserName());
            } else user = new UserEntity();

            var userSectorList = sectorService.collectSectorsFromIdList(selectedSectorList);
            user.setUserName(userDto.getUserName());
            user.setSectors(userSectorList);
            user.setAgreedToTerms(userDto.getAgreedToTerms());
            userRepository.save(user);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User has been successfully created!"));
        }  catch (Exception e) {
            log.error("Failed to create user with exception: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateUser(String selectedSectorList, UserDto userDto) throws Exception {
        try {
            var user = userRepository.findByUserName(userDto.getUserName());
            if (user == null) {
                throw new Exception("Failed to update user with exception: User not found with name " + userDto.getUserName());
            }
            if (selectedSectorList == null) {
                throw new Exception("Failed to update user with exception: No sectors chosen by the user.");
            }

            user.setSectors(sectorService.collectSectorsFromIdList(selectedSectorList));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User updated successfully!")
                    );
        } catch(Exception e) {
            log.error("Failed to update user {} with exception {}", userDto.getUserName(), e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Long> getUserSectorIdList(UserDto userDto) throws Exception {
        var user = userRepository.findByUserName(userDto.getUserName());
        if (user == null) {
            throw new Exception("Failed to get user sector id list user with exception: User not found with name " + userDto.getUserName());
        }
        var userSectors = user.getSectors();
        if (userSectors.isEmpty()) {
            throw new Exception("Failed to get user sector id list with exception: Sector list not found for user {}" + userDto.getUserName());
        }

        List<Long> userSectorIdList = new ArrayList<>();
        for (SectorEntity sectorEntity : userSectors) {
            var sectorId = sectorEntity.getId();
            userSectorIdList.add(sectorId);
        }

        return userSectorIdList;
    }

}
