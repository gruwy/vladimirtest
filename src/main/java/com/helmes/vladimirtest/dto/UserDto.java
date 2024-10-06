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
public class UserDto {

    @NotNull
    private long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String userName;

    @NotNull
    private boolean agreedToTerms;

    private List<SectorEntity> sectors;
}
