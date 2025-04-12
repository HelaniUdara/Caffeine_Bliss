package com.ex.caffeine_bliss.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class MessagingException extends RuntimeException {
    public MessagingException(String message){
        super(message);
    }
}
