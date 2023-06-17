package com.usecase4.IncidentManagement.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerClass {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
        ErrorResponse errorResponse = new ErrorResponse("No element with the specified ID exists");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        System.out.println(ex.toString());
        System.out.println(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("An error occurred");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNonExistentEndpoints(HttpServletRequest request) {
        String message = "Endpoint not found: " + request.getRequestURI();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<String> handleConstrainException(TransactionSystemException ex) {
        System.out.println(ex.getRootCause());

        if (ex.getRootCause() instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex.getRootCause();
            System.out.println(constraintViolationException.getConstraintViolations());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(constraintViolationException.getConstraintViolations().toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Required fields missing");
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleNonExistingENUMException(HttpMessageNotReadableException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());
    }
}
