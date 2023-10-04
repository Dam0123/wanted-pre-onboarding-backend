package com.wanted.exception;

import com.wanted.exception.common.BaseExceptionType;

public enum PostExceptionType implements BaseExceptionType {
    POST_DOES_NOT_EXIST(404, "채용공고 정보를 찾을 수 없습니다."),
    UNAUTHORIZED_ACCESS(403, "해당 공고의 수정 권한이 없습니다.");

    private int errorCode;
    private String errorMsg;

    PostExceptionType(int errorCode, String errorMsg){
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
