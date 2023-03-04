package com.skypro.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<MessageForException> handleException (BookException bookException) {

        MessageForException message = new MessageForException();
        message.setMessage(bookException.getMessage());

        return new ResponseEntity<MessageForException>(message, HttpStatus.NOT_FOUND);
    }

}