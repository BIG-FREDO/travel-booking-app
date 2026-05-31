package com.fredo.book_travel.exception;

import com.fredo.book_travel.dto.response.ErrorResponse;
import com.fredo.book_travel.exception.customExceptions.InvalidCredentialsException;
import com.fredo.book_travel.exception.customExceptions.InvalidRoleException;
import com.fredo.book_travel.exception.customExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //This is a global exception handler that catches all exceptions that is thrown by a custom exception handlers
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){

        //SETTING THE VALUES TO THE RECORD FOR A COMPREHENSIVE RESPONSE
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(), //MESSAGE
                HttpStatus.NOT_FOUND.value(), //STATUS CODE
                LocalDateTime.now()); //TIMESTAMP

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRoleException(InvalidRoleException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
