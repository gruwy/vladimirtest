package com.helmes.vladimirtest.dto;

import jakarta.validation.constraints.AssertTrue;
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
public class UserDto {

    @NotNull
    private long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String userName;

    @NotNull
    @AssertTrue
    private boolean agreedToTerms;

}
