package com.helmes.vladimirtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectorFormDto {

    private Long id;
    private Long userId;
    private boolean agreeOnTerms;

}
