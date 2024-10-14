package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.entity.UserEntity;
import com.helmes.vladimirtest.mapper.UserMapper;
import com.helmes.vladimirtest.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SectorService sectorService;
    private UserMapper userMapper;

    @Override
    public UserDto saveUser(String selectedSectorList, UserDto userDto) throws Exception {
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
            var savedUser = userRepository.save(user);
            return userMapper.toDto(savedUser);

        }  catch (Exception e) {
            log.error("Failed to create user with exception: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public Optional<UserDto> updateUser(String selectedSectorList, UserDto userDto) throws Exception {
        try {
            var user = userRepository.findByUserName(userDto.getUserName());
            if (user == null) {
                throw new Exception("Failed to update user with exception: User not found with name " + userDto.getUserName());
            }
            if (selectedSectorList == null) {
                throw new Exception("Failed to update user with exception: No sectors chosen by the user.");
            }
            user.setSectors(sectorService.collectSectorsFromIdList(selectedSectorList));
            var savedUser = userRepository.save(user);
            return Optional.of(userMapper.toDto(savedUser));

        } catch(Exception e) {
            log.error("Failed to update user {} with exception {}", userDto.getUserName(), e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Long> getUserSectorIdList(UserDto userDto) throws Exception {
        try {
        var user = userRepository.findByUserName(userDto.getUserName());
        if (user == null) {
            throw new Exception("Failed to get user sector id list with exception: User not found with name " + userDto.getUserName());
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
        } catch(Exception e) {
            log.error("Failed to get user {} sector id list with exception {}", userDto.getUserName(), e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

}
