package com.pantala.todo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        return new ResponseEntity<>(new ExceptionResponse("Unexpected server error", ""), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundExceptions(NotFoundException ex, WebRequest request){
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), ex.getDetails()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<Object> handleConflictExceptions(ConflictException ex, WebRequest request){
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), ex.getDetails()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<Object> handleUnauthenticatedException(UnauthorizedException ex, WebRequest request){
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), ex.getDetails()), HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationErrors = ex.getBindingResult().getFieldErrors().stream().map(f -> String.format("%s %s",f.getField(),f.getDefaultMessage())).collect(Collectors.toList());
        return new ResponseEntity<>(new ExceptionResponse("Validation failed", validationErrors), HttpStatus.BAD_REQUEST);
    }


}
