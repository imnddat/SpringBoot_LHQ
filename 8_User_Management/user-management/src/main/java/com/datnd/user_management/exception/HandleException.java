package com.datnd.user_management.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.datnd.user_management.exception.custom.CustomBadRequestException;
import com.datnd.user_management.exception.custom.CustomNotFoundException;
import com.datnd.user_management.model.CustomError;

@RestControllerAdvice
public class HandleException {
    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, CustomError> notFoundException(CustomNotFoundException ex) {
        return ex.getErrors();
    }

    @ExceptionHandler(CustomBadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, CustomError> badRequestException(CustomBadRequestException ex) {
        return ex.getErrors();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, CustomError> commonException(Exception ex) {
        Map<String, CustomError> map = new HashMap<>();
        map.put("error", CustomError.builder().code("500").message("Something went worng").build());
        return map;
    }

}
