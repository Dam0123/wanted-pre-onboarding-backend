package com.wanted.exception;

import com.wanted.exception.common.BaseExceptionType;

public enum CompanyExceptionType implements BaseExceptionType {
    COMPANY_DOES_NOT_EXIST(404, "회사 정보를 찾을 수 없습니다.");


    private int errorCode;
    private String errorMsg;

    CompanyExceptionType(int errorCode, String errorMsg){
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
