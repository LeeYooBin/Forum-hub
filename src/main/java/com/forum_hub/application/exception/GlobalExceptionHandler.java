package com.forum_hub.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

import java.util.stream.Collectors;

import javax.security.sasl.AuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    var errors = ex.getBindingResult().getFieldErrors().stream()
      .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
      .collect(Collectors.toList());
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
    var errors = ex.getConstraintViolations().stream()
      .map(violation -> new ValidationError(violation.getPropertyPath().toString(), violation.getMessage()))
      .collect(Collectors.toList());
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
    return ResponseEntity.internalServerError().body(ex.getMessage());
  }

  private record ValidationError(String field, String message) {}
}
