package com.helmes.vladimirtest.service;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.mapper.UserMapper;
import com.helmes.vladimirtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto saveUser(Model model, UserDto userDto) {
        var user = userMapper.toEntity(userDto);
        var savedUser = userRepository.save(user);
        UserDto dto = userMapper.toDto(savedUser);
        model.addAttribute("userDto", dto);
        return dto;
    }


}
