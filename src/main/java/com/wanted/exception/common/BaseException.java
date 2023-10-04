package com.wanted.exception.common;

public abstract class BaseException extends RuntimeException {
    public abstract BaseExceptionType getExceptionType();
}
