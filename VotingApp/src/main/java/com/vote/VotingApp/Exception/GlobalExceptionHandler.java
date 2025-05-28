package com.vote.VotingApp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handelResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        ErrorResponse err=new ErrorResponse(HttpStatus.NOT_FOUND.value(), resourceNotFoundException.getMessage());
        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handelDuplicateResourceException(DuplicateResourceException duplicateResourceException){
        ErrorResponse err=new ErrorResponse(HttpStatus.CONFLICT.value(), duplicateResourceException.getMessage());
        return new ResponseEntity<>(err,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(VoteNotAllowedException.class)
    public ResponseEntity<ErrorResponse> voteNotAllowedException(VoteNotAllowedException voteNotAllowedException){
        ErrorResponse err=new ErrorResponse(HttpStatus.FORBIDDEN.value(), voteNotAllowedException.getMessage());
        return new ResponseEntity<>(err,HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String,String> errors=new HashMap<>();
        BindingResult bindingResult=methodArgumentNotValidException.getBindingResult();
        List<FieldError> errorList=bindingResult.getFieldErrors();
        for (FieldError err:errorList){
            errors.put(err.getField(),err.getDefaultMessage());
        }
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handelException(Exception exception){
        ErrorResponse err=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
