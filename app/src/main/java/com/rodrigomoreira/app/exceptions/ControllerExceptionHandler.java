package com.rodrigomoreira.app.exceptions;

import com.rodrigomoreira.app.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> threatDuplicateEntry(DataIntegrityViolationException exception) {

        String message = exception.getRootCause() != null ? exception.getRootCause().getMessage() : exception.getMessage();
        String newMessage = "";

        if (message.contains("unique_registration")) {
            newMessage = "Registration already done!";
        }

        else if (message.contains("check_name_not_empty") || message.contains("check_registration_not_empty")) {
            newMessage = "Fill in all fields";
        }

        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }

        ExceptionDTO exceptionDTO = new ExceptionDTO(newMessage);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDTO);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> threat404(EntityNotFoundException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> threatGeneralException(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage());
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

}