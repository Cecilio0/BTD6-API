package com.dread9182.BTD6API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	private final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
	
	@ExceptionHandler(value = {NotFoundException.class})
	public ResponseEntity<Object> handleNotFoundException(NotFoundException e){
		ApiException apiException = new ApiException(
				e.getMessage(),
				badRequest
		);
		
		return new ResponseEntity<>(apiException, badRequest);
	}
	
	@ExceptionHandler(value = {ValueNotValidException.class})
	public ResponseEntity<Object> handleValueNotValidException(ValueNotValidException e){
		ApiException apiException = new ApiException(
				e.getMessage(),
				badRequest
		);
		
		return new ResponseEntity<>(apiException, badRequest);
	}
}
