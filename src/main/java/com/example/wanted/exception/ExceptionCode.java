package com.example.wanted.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum ExceptionCode {
    COMPANY_NOT_FOUND(404, "등록되지않은 회사입니다.");

    @Getter
    private int status;

    @Getter
    private  String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }


}
