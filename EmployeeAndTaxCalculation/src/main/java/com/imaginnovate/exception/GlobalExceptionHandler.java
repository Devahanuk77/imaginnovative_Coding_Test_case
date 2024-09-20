package com.imaginnovate.exception;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	   @ExceptionHandler(MethodArgumentNotValidException.class)
	   public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exp){
		  Map<String, String> errors = new HashMap<>();
	        exp.getBindingResult().getAllErrors().forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return ResponseEntity.badRequest().body(errors);
		
	}
	   

	    @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException exp) {
	        Map<String, String> errors = new HashMap<>();
	        exp.getConstraintViolations().forEach(violation -> {
	            String fieldName = violation.getPropertyPath().toString();
	            String errorMessage = violation.getMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	    }
	    
	    @ExceptionHandler(DataIntegrityViolationException.class)
	    @ResponseStatus(HttpStatus.CONFLICT)
	    public ResponseEntity<Map<String, String>> handleUniqueConstraintViolation(DataIntegrityViolationException ex) {
	        Map<String, String> error = new HashMap<>();
	        error.put("error", "Employee ID must be unique. This Employee ID already exists.");
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	    }

	    
	    @ExceptionHandler(DateTimeParseException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseEntity<Map<String, String>> handleDateTimeParseException(DateTimeParseException ex) {
	        Map<String, String> error = new HashMap<>();
	        error.put("doj", "doj must be in the format yyyy-MM-dd");
	        return ResponseEntity.badRequest().body(error);
	    }


}
