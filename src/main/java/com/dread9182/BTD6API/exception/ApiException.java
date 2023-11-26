package com.dread9182.BTD6API.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ApiException {
	@Getter
	private final String message;
	
	@Getter
	private final HttpStatus httpStatus;
	
	
}
