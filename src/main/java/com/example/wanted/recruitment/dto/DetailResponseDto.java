package com.example.wanted.recruitment.dto;

import com.example.wanted.recruitment.entity.Recruitment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DetailResponseDto {
    private Long id;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private String reward;
    private String skill;
    private String description;
    private List<Long> otherRecruitment;
}
