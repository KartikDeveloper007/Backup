package com.uoons.users.advice;

import com.uoons.users.exception.EmptyInput;
import com.uoons.users.exception.NotFound;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyInput.class)
    public ResponseEntity<String> handleEmptyInput(EmptyInput e) {
        return new ResponseEntity<String>("fields are empty!!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> handleNotFound(NotFound notFound) {
        return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
    }

    /*@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleNotFound(DataIntegrityViolationException e) {
        return new ResponseEntity<String>("Duplicate Entry! Please try another email", HttpStatus.BAD_REQUEST);
    }*/




    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEnitityNotFound(EntityNotFoundException e) {
        return new ResponseEntity<String>("Employee not present in Database", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        return new ResponseEntity<String>("Email not present in Database", HttpStatus.BAD_REQUEST);
    }
}
