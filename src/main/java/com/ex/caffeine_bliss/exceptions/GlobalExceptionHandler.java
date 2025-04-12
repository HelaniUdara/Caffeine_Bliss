package com.ex.caffeine_bliss.exceptions;

import com.ex.caffeine_bliss.utils.StandardResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "ERROR",
                        ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateElementException.class)
    public ResponseEntity<StandardResponse> handleDuplicateElementException(DuplicateElementException ex) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(409, "ERROR",
                        ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<StandardResponse> handleMessagingException(MessagingException ex) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(417, "ERROR",
                        ex.getMessage()), HttpStatus.EXPECTATION_FAILED);
    }
}
