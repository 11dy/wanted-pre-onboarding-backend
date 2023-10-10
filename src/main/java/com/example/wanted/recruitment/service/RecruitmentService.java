package com.example.wanted.recruitment.service;

import com.example.wanted.recruitment.dto.MultiResponseDto;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.mapper.RecruitmentMapper;
import com.example.wanted.recruitment.repository.RecruitmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentMapper mapper;

    public RecruitmentService(RecruitmentRepository recruitmentRepository,
                              RecruitmentMapper mapper){
        this.recruitmentRepository = recruitmentRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Object findAll(Long id, int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);

        Page<Recruitment> recruitments = recruitmentRepository.findAllById(id, pageable);
        List<Recruitment> recruitmentList = recruitments.getContent();

        return new MultiResponseDto<>(
                mapper.recruitmentToRecruitmentResponseDtos(recruitmentList),
                recruitments);

    }
}
