package com.parcialProgramacion.controller.exceptionHandler;


import com.parcialProgramacion.controller.exceptionHandler.exception.InvalidDnaSequenceException;
import com.parcialProgramacion.controller.exceptionHandler.exception.NoDnaFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoDnaFoundException.class)
    public ResponseEntity<String> handleNoDnaFoundException(NoDnaFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(InvalidDnaSequenceException.class)
    public ResponseEntity<String> invalidDnaException(InvalidDnaSequenceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}

