package com.wanted.exception;

import com.wanted.exception.common.BaseExceptionType;

public enum UserExceptionType implements BaseExceptionType {

    USER_DOES_NOT_EXIST(404, "사용자 정보를 찾을 수 없습니다."),
    UNAUTHORIZED_ACCESS(403, "권한이 없는 사용자입니다.");

    private int errorCode;
    private String errorMsg;

    UserExceptionType(int errorCode, String errorMsg){
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
