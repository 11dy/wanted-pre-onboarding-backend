package com.example.wanted.recruitment.mapper;

import com.example.wanted.recruitment.dto.DetailResponseDto;
import com.example.wanted.recruitment.dto.RecruitmentPatchDto;
import com.example.wanted.recruitment.dto.RecruitmentPostDto;
import com.example.wanted.recruitment.dto.RecruitmentResponseDto;
import com.example.wanted.recruitment.entity.Company;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecruitmentMapper {

    private final CompanyRepository companyRepository;

    // list
    public RecruitmentResponseDto recruitmentToRecruitmentResponseDto(Recruitment recruitment){
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


        return recruitmentResponseDto;
    }

    public List<RecruitmentResponseDto> recruitmentToRecruitmentResponseDtos(List<Recruitment> recruitmentList){
        return recruitmentList.stream()
                .map(recruitment -> recruitmentToRecruitmentResponseDto(recruitment))
                .collect(Collectors.toList());
    }

    // post
    public Recruitment RecruitmentPostDtoToRecruitment(RecruitmentPostDto requestBody){
        if(requestBody == null){
            return null;
        }
        Recruitment recruitment = new Recruitment();
        Company company = companyRepository.findById(requestBody.getCompanyId()).orElseThrow(
                IllegalArgumentException::new
        );
        recruitment.setCompany(company);
        recruitment.setDescription(requestBody.getDescription());
        recruitment.setPosition(requestBody.getPosition());
        recruitment.setReward(requestBody.getReward());
        recruitment.setSkill(requestBody.getSkill());

        return recruitment;
    }

    public RecruitmentResponseDto.Post recruitmentToRecruitmentResponseDToPost(Recruitment recruitment){
        if(recruitment == null){
            return null;
        }
        RecruitmentResponseDto.Post postResponseDto = new RecruitmentResponseDto.Post(
                recruitment.getCompany().getId(),
                recruitment.getPosition(),
                recruitment.getReward(),
                recruitment.getDescription(),
                recruitment.getCompany().getSkill()
        );

        return postResponseDto;
    }

    // update
    public Recruitment recruitmentPatchDtoToRecruitment(RecruitmentPatchDto requestBody){
        if(requestBody == null){
            return null;
        }
        Recruitment recruitment = new Recruitment();
        recruitment.setPosition(requestBody.getPosition());
        recruitment.setSkill(requestBody.getSkill());
        recruitment.setReward(requestBody.getReward());
        recruitment.setDescription(requestBody.getDescription());

        return recruitment;
    }

    public RecruitmentResponseDto.Patch recruitmentToRecruitmentResponseDtoPatch(Recruitment patchRecruitment) {
        if(patchRecruitment == null){
            return null;
        }
        RecruitmentResponseDto.Patch patchResponseDto = new RecruitmentResponseDto.Patch(
                patchRecruitment.getPosition(),
                patchRecruitment.getDescription(),
                patchRecruitment.getReward(),
                patchRecruitment.getSkill()
        );


        return patchResponseDto;
    }

    // detail
    public DetailResponseDto recruitmentToDetailResponseDto(Recruitment recruitment){
        if(recruitment == null){
            return null;
        }
        List<Long> recruitmentIds = new ArrayList<>();
        for(Recruitment r: recruitment.getCompany().getRecruitments()){
            recruitmentIds.add(r.getId());
        }

        DetailResponseDto detailResponseDto= new DetailResponseDto(
                recruitment.getId(),
                recruitment.getCompany().getCompanyName(),
                recruitment.getCompany().getCountry(),
                recruitment.getCompany().getRegion(),
                recruitment.getPosition(),
                recruitment.getReward(),
                recruitment.getCompany().getSkill(),
                recruitment.getDescription(),
                recruitmentIds
        );
        return detailResponseDto;
    }

}
