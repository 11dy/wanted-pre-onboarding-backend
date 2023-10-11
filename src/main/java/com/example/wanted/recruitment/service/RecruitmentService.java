package com.example.wanted.recruitment.service;

import com.example.wanted.exception.BusinessLogicException;
import com.example.wanted.exception.ExceptionCode;
import com.example.wanted.recruitment.dto.MultiResponseDto;
import com.example.wanted.recruitment.entity.Company;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.mapper.RecruitmentMapper;
import com.example.wanted.recruitment.repository.CompanyRepository;
import com.example.wanted.recruitment.repository.RecruitmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.mysql.cj.conf.PropertyKey.logger;

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
    public Object findAll( int page, int size){
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
//        Company findCompany = companyOptional.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMPANY_NOT_FOUND));
        return companyOptional.isPresent();
    }

}
