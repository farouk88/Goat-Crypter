package dev.watermelon.goatcrypter.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloabalExceptionHandler {
    
    // Handle duplicate database entries 
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDuplicateDataException(DataIntegrityViolationException e){
        String message = "The username or email already exists. Please choose a different one";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    // Handle other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e){
        return new ResponseEntity<>("An enexpted error occured: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}