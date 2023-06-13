package com.usecase4.IncidentManagement.exception;

public class NoResourceFoundException extends RuntimeException {
    public NoResourceFoundException(String message) {
        super(message);
    }
}
