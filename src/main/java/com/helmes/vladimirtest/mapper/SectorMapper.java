package com.helmes.vladimirtest.mapper;

import com.helmes.vladimirtest.dto.SectorDto;
import com.helmes.vladimirtest.entity.SectorEntity;
import com.helmes.vladimirtest.mapper.base.EntityMapper;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectorMapper extends EntityMapper<SectorDto, SectorEntity> {
    @Override
    SectorEntity toEntity(SectorDto dto);
    @Override
    SectorDto toDto(SectorEntity entity);
    @Override
    List<SectorEntity> toEntity(List<SectorDto> dtoList);
    @Override
    List<SectorDto> toDto(List<SectorEntity> entityList);
}
