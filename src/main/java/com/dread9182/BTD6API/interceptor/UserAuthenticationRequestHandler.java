package com.dread9182.BTD6API.interceptor;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.user.model.request.UserAuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.stream.Collectors;

public class UserAuthenticationRequestHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String bodyString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		UserAuthenticationRequest userAuthenticationRequest = new ObjectMapper().readValue(bodyString, UserAuthenticationRequest.class);
		
		if(userAuthenticationRequest.getEmail() == null || userAuthenticationRequest.getEmail().equals("")){
			throw new ValueNotValidException("The email field on a user authentication request cannot be null");
		} else if(userAuthenticationRequest.getPassword() == null || userAuthenticationRequest.getPassword().equals("")){
			throw new ValueNotValidException("The password field on a user authentication request cannot be null");
		}
		
		return true;
	}
}
