package com.dread9182.BTD6API.interceptor;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.user.model.request.UserRegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.stream.Collectors;

@Slf4j
public class UserRegisterRequestHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String bodyString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		log.info(bodyString);
		UserRegisterRequest userRegisterRequest = new ObjectMapper().readValue(bodyString, UserRegisterRequest.class);
		
		if(userRegisterRequest.getFirstName() == null || userRegisterRequest.getFirstName().equals("")){
			throw new ValueNotValidException("The firstName field on a user register request cannot be null");
		} else if(userRegisterRequest.getLastName() == null || userRegisterRequest.getLastName().equals("")){
			throw new ValueNotValidException("The lastName field on a user register request cannot be null");
		} else if(userRegisterRequest.getEmail() == null || userRegisterRequest.getEmail().equals("")){
			throw new ValueNotValidException("The email field on a user register request cannot be null");
		} else if(userRegisterRequest.getPassword() == null || userRegisterRequest.getPassword().equals("")){
			// todo verify that the password is strong enough
			throw new ValueNotValidException("The password field on a user register request cannot be null");
		}
		
		return true;
	}
}