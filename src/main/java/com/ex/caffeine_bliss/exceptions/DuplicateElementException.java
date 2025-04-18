package com.ex.caffeine_bliss.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateElementException extends RuntimeException{
    public DuplicateElementException(String message){
        super(message);
    }
}
