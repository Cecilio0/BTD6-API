package com.dread9182.BTD6API.interceptor.bloon;

import com.dread9182.BTD6API.bloon.model.BloonTypes;
import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.hero.model.HeroUnlockHow;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

public class BloonTypeRequestHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String type = uri.split("/")[3];
		
		if(Arrays.stream(BloonTypes.values()).noneMatch((validType) -> type.equals(validType.name())))
			throw new ValueNotValidException("The how value has to be either bloon or moab");
		
		return true;
	}
}
