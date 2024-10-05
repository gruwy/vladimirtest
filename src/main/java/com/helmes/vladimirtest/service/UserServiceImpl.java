package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.entity.UserEntity;
import com.helmes.vladimirtest.mapper.UserMapper;
import com.helmes.vladimirtest.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SectorService sectorService;

    @Transactional
    @Override
    public UserDto saveUser(Model model, String selectedSectorIdString, UserDto userDto) {
        userDto.setSectors(sectorService.collectSectorsFromIdList(selectedSectorIdString));
        var user = userMapper.toEntity(userDto);
        userRepository.save(user);
        model.addAttribute("userDto", userDto);
        return userDto;
    }

    @Transactional
    @Override
    public Optional<UserDto> updateUser(Model model, String selectedSectorIdString, UserDto userDto) {
        return getUserEntityByUserName(userDto).map(
                userEntity -> {
                    userEntity.setSectors(sectorService.collectSectorsFromIdList(selectedSectorIdString));
                    var savedUser = userRepository.save(userEntity);
                    var dto = userMapper.toDto(savedUser);
                    model.addAttribute("userDto", dto);
                    return dto;
                }
        );
    }

    @Override
    public Model refillUserData(Model model, UserDto userDto) {
        var user = getUserEntityByUserName(userDto);
        var userSectors = getUserSectors(userDto);
        userDto.setSectors(userSectors);
        userDto.setAgreedToTerms(user.get().isAgreedToTerms());

        model.addAttribute("userDto", userDto);
        return model;
    }

    @Override
    public List<Long> getUserSectorIdList(UserDto userDto) {
        var userSectors = getUserSectors(userDto);
        List<Long> userSectorIdList = new ArrayList<>();
        for (SectorEntity sectorEntity : userSectors) {
            var sectorId = sectorEntity.getId();
            userSectorIdList.add(sectorId);
        }
        return userSectorIdList;
    }

    @Override
    public List<SectorEntity> getUserSectors(UserDto userDto) {
        var user = getUserEntityByUserName(userDto);
        return user.get().getSectors();
    }

    @Override
    public Optional<UserEntity> getUserEntityByUserName(UserDto userDto) {
        return userRepository.findById(userRepository.findUserIdByUserName(userDto.getUserName()));
    }
}
