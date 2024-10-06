package com.helmes.vladimirtest.dto;

import com.helmes.vladimirtest.entity.SectorEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectorDto {

    @NotNull
    private long id;

    private SectorEntity parentSectorId;

    private List<SectorEntity> subSectors;

    @NotNull
    @Size(min = 1, max = 255)
    private String sectorName;
}
