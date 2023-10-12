package com.example.wanted.recruitment.service;

import com.example.wanted.exception.BusinessLogicException;
import com.example.wanted.exception.ExceptionCode;
import com.example.wanted.recruitment.dto.util.MultiResponseDto;
import com.example.wanted.recruitment.dto.util.SingleResponseDto;
import com.example.wanted.recruitment.entity.Company;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.mapper.RecruitmentMapper;
import com.example.wanted.recruitment.repository.CompanyRepository;
import com.example.wanted.recruitment.repository.RecruitmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentMapper mapper;
    private final CompanyRepository companyRepository;
    private static final Logger logger = LoggerFactory.getLogger(RecruitmentService.class);

    public RecruitmentService(RecruitmentRepository recruitmentRepository,
                              RecruitmentMapper mapper,
                              CompanyRepository companyRepository){
        this.recruitmentRepository = recruitmentRepository;
        this.mapper = mapper;
        this.companyRepository = companyRepository;
    }

    @Transactional(readOnly = true)
    public Object findAll(int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);

        Page<Recruitment> recruitments = recruitmentRepository.findAll(pageable);
        List<Recruitment> recruitmentList = recruitments.getContent();

        return new MultiResponseDto<>(
                mapper.recruitmentToRecruitmentResponseDtos(recruitmentList),
                recruitments);

    }

    public Recruitment createRecruitment(Long companyId, Recruitment recruitment) {
        //companyid를 통해 회사가 존재하는지 파악
        boolean exist = findPostedCompany(companyId); // 회사가 존재여부 판단
        if(!exist){
            throw new BusinessLogicException(ExceptionCode.COMPANY_NOT_FOUND);
        }
        return recruitmentRepository.save(recruitment);
    }

    public Boolean findPostedCompany(Long companyId){
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        return companyOptional.isPresent();
    }


    public Recruitment updateRecruitment(Recruitment recruitment, Long recruitmentId) {
        Recruitment findRecruitment = findRecruitment(recruitmentId);
        Optional.ofNullable(recruitment.getPosition())
                .ifPresent(recruitmentPosition -> findRecruitment.setPosition(recruitmentPosition));
        Optional.ofNullable(recruitment.getReward())
                .ifPresent(recruitmentReward -> findRecruitment.setReward(recruitmentReward));
        Optional.ofNullable(recruitment.getDescription())
                .ifPresent(recruitmentDescription -> findRecruitment.setDescription(recruitmentDescription));
        Optional.ofNullable(recruitment.getSkill())
                .ifPresent(recruitmentSkill -> findRecruitment.setSkill(recruitmentSkill));
        Recruitment modifiedRecruitment = recruitmentRepository.save(findRecruitment);

        return modifiedRecruitment;
    }

    // 채공공고 존재 판단 메서드
    @Transactional
    public Recruitment findRecruitment(Long recruitmentId){
        Optional<Recruitment> optionalRecruitment =
                recruitmentRepository.findById(recruitmentId);
        Recruitment findRecruitment =
                optionalRecruitment.orElseThrow(IllegalArgumentException::new);
        return findRecruitment;
    }

    public void deleteRecruitment(Long recruitmentId) {
        Recruitment recruitment = findRecruitment(recruitmentId);
        recruitmentRepository.delete(recruitment);
    }

    // 채용상세 페이지
    @Transactional(readOnly = true)
    public Object findDetail(Long recruitmentId){
        Optional<Recruitment> detailPage = recruitmentRepository.findById(recruitmentId);
        Recruitment result = detailPage.orElseThrow(IllegalArgumentException::new);
        return new SingleResponseDto<>(mapper.recruitmentToDetailResponseDto(result));
    }

    // 채용공고 검색
    @Transactional(readOnly = true)
    public Object searchRecruitment(int page, int size, String keyword){
        Pageable pageable = PageRequest.of(page-1, size);

        Page<Recruitment> searchRecruitments = recruitmentRepository.searchByKeyword(keyword, pageable);
        List<Recruitment> recruitmentList = searchRecruitments.getContent();

        return new MultiResponseDto<>(
                mapper.recruitmentToRecruitmentResponseDtos(recruitmentList),
                searchRecruitments);
    }
}
