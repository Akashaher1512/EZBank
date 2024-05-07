package com.akash.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFountException extends RuntimeException{

    public ResourceNotFountException(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s not fount with the given input data %s : '%s'",resourceName,fieldName,fieldValue));
    }
}
