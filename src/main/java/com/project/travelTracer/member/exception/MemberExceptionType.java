package com.project.travelTracer.member.exception;

import com.project.travelTracer.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

// 회원 관련 예외의 종류를 정의하는 MemberExceptionType 열거형(Enum) 클래스
public enum MemberExceptionType implements BaseExceptionType {

    ALREADY_EXIST_USERID(600, HttpStatus.OK, "이미 존재하는 아이디입니다"),
    WRONG_PASSWORD(601,HttpStatus.OK, "비밀번호가 잘못되었습니다."),
    NOT_FOUND_MEMBER(602, HttpStatus.OK, "회원 정보가 없습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    MemberExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this. getHttpStatus();
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
