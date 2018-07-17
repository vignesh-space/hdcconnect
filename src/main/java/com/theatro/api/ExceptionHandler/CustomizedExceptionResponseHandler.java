package com.theatro.api.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {

        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;

    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotfoundException(Exception ex, WebRequest req) {

        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.NOT_FOUND);
        return responseEntity;

    }

}
