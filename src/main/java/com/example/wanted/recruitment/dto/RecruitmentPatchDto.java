package com.example.wanted.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class RecruitmentPatchDto {
    private String position;
    private String reward;
    private String description;
    private String skill;
}
