package com.ortega.tshomboapi.controller;

import com.ortega.tshomboapi.util.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        // Log the exception for debugging
        log.error("An error occurred:", ex);
        return ResponseHandler.response(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
    }
}