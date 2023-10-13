package com.example.wanted.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post{
        private Long companyId;
        private String position;
        private String reward;
        private String description;
        private String skill;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch{
        private String position;
        private String reward;
        private String description;
        private String skill;
    }
}
