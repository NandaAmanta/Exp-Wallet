/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.exceptions;

import com.exp.ewallet.applications.response.v1.Response;
import com.exp.ewallet.applications.response.v1.ResponseError;
import com.exp.ewallet.exceptions.custom.ValidationNumberException;
import io.jsonwebtoken.SignatureException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.NoResultException;
import javax.security.auth.login.AccountException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *
 * @author ASUS
 */
@RestControllerAdvice
@Slf4j
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AuthenticationException.class, SignatureException.class, UsernameNotFoundException.class})
    public ResponseEntity<Response> handleAuthenticationException() {
        ResponseError response = new ResponseError();
        response.setMessage("You are not authenticated, need a valid token");
        response.setErrors(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Response> handleNoResultException(NoResultException exception) {
        ResponseError response = new ResponseError();
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Response> handleNoSuchElementException(NoSuchElementException exception) {
        ResponseError response = new ResponseError();
        response.setMessage(exception.getMessage() == null ? "Data not found" : exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ValidationNumberException.class)
    public ResponseEntity<Response> handleValidationNumberException(ValidationNumberException exception) {
        ResponseError response = new ResponseError();
        response.setMessage(exception.getMessage());
        response.setErrors(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Response> handleConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        ResponseError response = new ResponseError();
        response.setMessage(exception.getMessage());
        response.setErrors(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(com.exp.ewallet.exceptions.custom.AccountException.class)
    public ResponseEntity<Response> handleAccountException(Exception exception) {
        ResponseError response = new ResponseError();
        response.setMessage(exception.getMessage());
        response.setErrors(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleIllegalArgumentException(IllegalArgumentException exception) {
        ResponseError response = new ResponseError();
        log.error(exception.getMessage());
        response.setMessage("Bad Request");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseError response = new ResponseError();
        response.setMessage("Method not allowed");
        response.setErrors(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseError response = new ResponseError();
        response.setMessage("Resource not found");
        response.setErrors(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        ResponseError response = new ResponseError();
        response.setMessage("Bad Request");
        response.setErrors(List.of("Invalid request body"));
        log.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ResponseError response = new ResponseError();
        log.error(ex.getMessage());
        response.setMessage("Internal Server Error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
