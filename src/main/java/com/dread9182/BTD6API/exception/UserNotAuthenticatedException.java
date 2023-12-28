package com.dread9182.BTD6API.exception;

public class UserNotAuthenticatedException extends RuntimeException{
	public UserNotAuthenticatedException(String message){
		super(message);
	}
}
