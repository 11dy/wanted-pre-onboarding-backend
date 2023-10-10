package com.example.wanted.recruitment.controller;

import com.example.wanted.recruitment.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
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

    public RecruitmentController(RecruitmentService recruitmentService){
        this.recruitmentService = recruitmentService;
    };
    // 채용공고 목록 가져오기
    @GetMapping
    public ResponseEntity<?> list(@RequestParam("page") int page,
                                 @RequestParam("size") int size){
        return new ResponseEntity<>(recruitmentService.findAll(page, size), HttpStatus.OK);
    }
    // 채용공고 등록
//    @PostMapping()
    // 채용공고 수정
    // 채용공고 삭제
    // 채용공고 검색 기능
    // 채용상세페이지
}
