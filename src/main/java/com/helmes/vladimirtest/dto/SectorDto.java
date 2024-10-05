package com.helmes.vladimirtest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectorDto {

    @NotNull
    private long id;

    private long parentSectorId;

    @NotNull
    @Size(min = 1, max = 255)
    private String sectorName;

}
