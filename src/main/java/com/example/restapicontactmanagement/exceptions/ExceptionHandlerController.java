package com.example.restapicontactmanagement.exceptions;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DuplicateContactException.class)
   // @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> HandleDuplicateContactException(DuplicateContactException e, WebRequest request){
        final ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(Instant.now())
        .error("Duplicate Contact")
        .status(HttpStatus.BAD_REQUEST.value())
        .message(e.getMessage())
        .path(request.getDescription(false))
        .build();
     return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
 
    @ExceptionHandler(EntityNotFoundException.class)
  //  @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody 
    public ResponseEntity<ErrorResponse> HandleEntityNotFoundException(EntityNotFoundException e, WebRequest request){
        final ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(Instant.now())
        .error("Invalid ID")
        .status(HttpStatus.NOT_FOUND.value())
        .message(e.getMessage())
        .path(request.getDescription(false))
        .build();
     return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    } 
//Any Other exception
    @ExceptionHandler(Exception.class)
   // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(Exception e, WebRequest request)    {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .error("Internal Server Error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
