package com.mastery.java.task.rest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mastery.java.task.exception.MyServiceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
public class ControllerAdvice {
    protected static final Logger log = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(MyServiceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNotFoundException(HttpServletRequest req, Exception ex) {
        log.error("Employee not found - request: {} raised {}", req.getRequestURL(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String pathVariableValidation(HttpServletRequest req, ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        log.error("Path variable validation - request: {} raised {} ", req.getRequestURL(), strBuilder, ex);
        return strBuilder.toString();
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String enumValidationException(HttpServletRequest req, InvalidFormatException ex) {
        log.error("Incorrect data in field - request: {} raised {}", req.getRequestURL(), ex);
        return "Incorrect data in field. " + ex.getOriginalMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String databaseError(HttpServletRequest req, Exception ex) {
        log.error("Error - request: {} raised {}", req.getRequestURL(), ex);
        return "Error";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public String requestError(HttpServletRequest req, Exception ex) {
        log.error("Request endpoint doesn't exists - request: {} raised {}", req.getRequestURL(), ex);
        return "Request endpoint doesn't exists";
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String numberFormatError(HttpServletRequest req, Exception ex) {
        log.error("Number format - request: {} raised {}", req.getRequestURL(), ex);
        return "Incorrect number format";
    }
}
