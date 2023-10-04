package com.wanted.exception;

import com.wanted.exception.common.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity handleBaseException(BaseException exception){

        log.error("BaseException errorMsg(): {}",exception.getExceptionType().getErrorMsg());

        return new ResponseEntity(
                new ExceptionDto(exception.getExceptionType().getErrorCode(),exception.getExceptionType().getErrorMsg()),
                HttpStatus.valueOf(exception.getExceptionType().getErrorCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        BindingResult bindingResult = exception.getBindingResult();
        StringBuilder builder = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getDefaultMessage());
        }
        log.error("BaseException errorMsg(): {}",builder.toString());

        ExceptionDto apiException = new ExceptionDto(
                400,builder.toString()
        );

        return new ResponseEntity<>(
                apiException,
                HttpStatus.BAD_REQUEST
        );
    }

    @Data
    @AllArgsConstructor
    static class ExceptionDto {
        private int errorCode;
        private String errorMsg;
    }

}

