package com.teste.pratico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

   
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        StringBuilder errorMessage = new StringBuilder("Erro de validação: ");
	        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
	            errorMessage.append(String.format("%s: %s; ", fieldError.getField(), fieldError.getDefaultMessage()));
	        }
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
	    }

}
