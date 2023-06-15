package com.usecase4.IncidentManagement.exception;

import jakarta.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlerClassTest {

    private ExceptionHandlerClass exceptionHandler;

    @Before
    public void setup() {
        exceptionHandler = new ExceptionHandlerClass();
    }

    @Test
    public void testHandleNoSuchElementException() {
        NoSuchElementException exception = new NoSuchElementException("No element with the specified ID exists");
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleNoSuchElementException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No element with the specified ID exists", response.getBody().getMessage());
    }

    @Test
    public void testHandleNoResourceFoundException() {
        NoResourceFoundException exception = new NoResourceFoundException("Resource not found");
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleNoResourceFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", response.getBody().getMessage());
    }

    @Test
    public void testHandleGeneralException() {
        Exception exception = new Exception("An error occurred");
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleGeneralException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred", response.getBody().getMessage());
    }

    @Test
    public void testHandleValidationException() {
        ValidationException exception = new ValidationException("Validation failed");
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleValidationException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Validation failed", response.getBody().getMessage());
    }
}
