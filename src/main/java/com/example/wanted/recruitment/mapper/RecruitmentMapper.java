package com.example.wanted.recruitment.mapper;

import com.example.wanted.recruitment.dto.RecruitmentResponseDto;
import com.example.wanted.recruitment.entity.Recruitment;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RecruitmentMapper {
    default  RecruitmentResponseDto recruitmentToRecruitmentResponseDto(Recruitment recruitment){
        if(recruitment == null){
            return null;
        }
        RecruitmentResponseDto recruitmentResponseDto = new RecruitmentResponseDto(
                recruitment.getId(),
                recruitment.getCompany().getCompanyName(),
                recruitment.getCompany().getCountry(),
                recruitment.getCompany().getRegion(),
                recruitment.getPosition(),
                recruitment.getReward(),
                recruitment.getCompany().getSkill()
        );
        recruitmentResponseDto.setId(recruitment.getId());
        recruitmentResponseDto.setCompanyName(recruitment.getCompany().getCompanyName());
        recruitmentResponseDto.setCountry(recruitment.getCompany().getCountry());
        recruitmentResponseDto.setRegion(recruitment.getCompany().getRegion());
        recruitmentResponseDto.setPosition(recruitment.getPosition());
        recruitmentResponseDto.setReward(recruitment.getReward());
        recruitmentResponseDto.setSkill(recruitment.getCompany().getSkill());

        return recruitmentResponseDto;
    }

    default List<RecruitmentResponseDto> recruitmentToRecruitmentResponseDtos(List<Recruitment> recruitmentList){
        return recruitmentList.stream()
                .map(recruitment -> recruitmentToRecruitmentResponseDto(recruitment))
                .collect(Collectors.toList());
    }
}
