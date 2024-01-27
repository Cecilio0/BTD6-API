package com.dread9182.BTD6API.interceptor;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.user.model.request.UserRegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class RegisterHandlerInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("We're in");
		return true;
	}
}
