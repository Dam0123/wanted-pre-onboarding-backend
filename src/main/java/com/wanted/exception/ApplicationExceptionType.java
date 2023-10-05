package com.wanted.exception;

import com.wanted.exception.common.BaseExceptionType;

public enum ApplicationExceptionType implements BaseExceptionType {
    ALREADY_APPLIED(409, "이미 지원한 채용공고입니다.");


    private int errorCode;
    private String errorMsg;

    ApplicationExceptionType(int errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
