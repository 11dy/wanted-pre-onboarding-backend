package com.example.wanted.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum ExceptionCode {
    COMPANY_NOT_FOUND(404, "등록되지않은 회사입니다."),
    ALREADY_APPLIED(404, "이미 지원한 공고입니다."),
    USER_NOT_FOUND(404,"존재하지 않는 사용자입니다."),
    RECRUITMENT_NOT_FOUND(404, "존재하지 않는 공고입니다.");

    @Getter
    private int status;

    @Getter
    private  String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }


}
