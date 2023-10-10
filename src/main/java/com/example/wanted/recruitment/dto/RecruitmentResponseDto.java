package com.example.wanted.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecruitmentResponseDto {

    private Long id;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private String reward;
    private String skill;
}
