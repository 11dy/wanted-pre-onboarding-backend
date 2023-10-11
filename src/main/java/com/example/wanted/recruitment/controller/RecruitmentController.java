package com.example.wanted.recruitment.controller;

import com.example.wanted.recruitment.dto.RecruitmentPatchDto;
import com.example.wanted.recruitment.dto.RecruitmentPostDto;
import com.example.wanted.recruitment.dto.RecruitmentResponseDto;
import com.example.wanted.recruitment.dto.SingleResponseDto;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.mapper.RecruitmentMapper;
import com.example.wanted.recruitment.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruitment")
@Validated
@Slf4j
public class RecruitmentController {
    private RecruitmentService recruitmentService;
    private RecruitmentMapper mapper;
    public RecruitmentController(RecruitmentService recruitmentService,
                                 RecruitmentMapper mapper){
        this.recruitmentService = recruitmentService;
        this.mapper = mapper;
    };
    // 채용공고 목록 가져오기
    @GetMapping
    public ResponseEntity<?> list(@RequestParam("page") int page,
                                 @RequestParam("size") int size){
        return new ResponseEntity<>(recruitmentService.findAll(page, size), HttpStatus.OK);
    }
    // 채용공고 등록
    @PostMapping("/{company-id}")
    public ResponseEntity<?> post(@PathVariable("company-id") Long companyId,
                                  @RequestBody RecruitmentPostDto requestBody){
        Recruitment recruitment = mapper.RecruitmentPostDtoToRecruitment(requestBody);
        Recruitment createRecruitment = recruitmentService.createRecruitment(companyId, recruitment);
        RecruitmentResponseDto.Post response = mapper.recruitmentToRecruitmentResponseDToPost(createRecruitment);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    // 채용공고 수정
    @PatchMapping("/update/{recruitment-id}")
    public ResponseEntity<?> update(@PathVariable("recruitment-id") Long recruitmentId,
                                    @RequestBody RecruitmentPatchDto requestBody){
        Recruitment patchRecruitment = recruitmentService.updateRecruitment(mapper.recruitmentPatchDtoToRecruitment(requestBody), recruitmentId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.recruitmentToRecruitmentResponseDtoPatch(patchRecruitment)), HttpStatus.OK);
    }
    // 채용공고 삭제
    // 채용공고 검색 기능
    // 채용상세페이지
}
