package com.example.wanted.recruitment.dto;

import com.example.wanted.recruitment.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecruitmentPostDto {
    private Long companyId;
    private String position;
    private String reward;
    private String description;
    private String skill;


}
