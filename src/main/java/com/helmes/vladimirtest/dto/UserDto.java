package com.helmes.vladimirtest.dto;

import com.helmes.vladimirtest.entity.SectorEntity;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Username is required!")
    @Size(min = 1, message = "Username must have atleast 1 character!")
    @Size(max = 255, message = "Username can have have atmost 255 characters!")
    private String userName;

    @AssertTrue(message = "Must agree to Terms!")
    private Boolean agreedToTerms;

    private List<SectorEntity> sectors;
}
