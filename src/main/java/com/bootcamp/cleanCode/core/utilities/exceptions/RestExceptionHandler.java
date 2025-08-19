package com.bootcamp.cleanCode.core.utilities.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bootcamp.cleanCode.core.utilities.ProblemDetails;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationProblemDetails> handleValidationException(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();

        for(FieldError fieldError : exception.getBindingResult()
        .getFieldErrors()){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ValidationProblemDetails problemDetails = new ValidationProblemDetails();
        problemDetails.setMessage("VALIDATION.EXCEPTİON"); //mesajları merkezi sistemden okuyacağız
        problemDetails.setValidationErrors(errors);

        return new ResponseEntity<>(problemDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetails> handleNotFoundExcepiton(NotFoundException exception){
        ProblemDetails details = new ProblemDetails();
        details.setMessage(exception.getMessage());

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> handleGenericException(Exception exception){
        ProblemDetails details= new ProblemDetails();
        details.setMessage("Internal.Server.Error");

        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
