package com.helmes.vladimirtest.mapper;

import com.helmes.vladimirtest.dto.UserDto;
import com.helmes.vladimirtest.entity.UserEntity;
import com.helmes.vladimirtest.mapper.base.EntityMapper;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, UserEntity> {
    @Override
    UserEntity toEntity(UserDto dto);
    @Override
    UserDto toDto(UserEntity entity);
    @Override
    List<UserEntity> toEntity(List<UserDto> dtoList);
    @Override
    List<UserDto> toDto(List<UserEntity> entityList);
}
