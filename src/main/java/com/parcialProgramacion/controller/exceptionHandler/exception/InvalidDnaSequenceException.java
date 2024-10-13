package com.parcialProgramacion.controller.exceptionHandler.exception;

public class InvalidDnaSequenceException extends RuntimeException {
    public InvalidDnaSequenceException(String message) {
        super(message);
    }
}
