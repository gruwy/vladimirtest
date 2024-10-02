package com.helmes.vladimirtest.mapper;

import com.helmes.vladimirtest.dto.SectorFormDto;
import com.helmes.vladimirtest.entity.SectorFormEntity;
import com.helmes.vladimirtest.mapper.base.EntityMapper;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SectorFormMapper extends EntityMapper<SectorFormDto, SectorFormEntity> {
    @Override
    SectorFormEntity toEntity(SectorFormDto dto);
    @Override
    SectorFormDto toDto(SectorFormEntity entity);
    @Override
    List<SectorFormEntity> toEntity(List<SectorFormDto> dtoList);
    @Override
    List<SectorFormDto> toDto(List<SectorFormEntity> entityList);
}
