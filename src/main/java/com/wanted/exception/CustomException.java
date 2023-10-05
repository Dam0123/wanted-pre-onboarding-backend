package com.wanted.exception;

import com.wanted.exception.common.BaseException;
import com.wanted.exception.common.BaseExceptionType;

public class CustomException extends BaseException {
    private BaseExceptionType exceptionType;

    public CustomException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
