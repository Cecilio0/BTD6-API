package com.dread9182.BTD6API.interceptor.hero;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.hero.model.HeroUnlockHow;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

public class HeroUnlockRequestHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String type = uri.split("/")[3];
		
		if(Arrays.stream(HeroUnlockHow.values()).noneMatch((validHow) -> type.equals(validHow.name())))
			throw new ValueNotValidException("The how value has to be either level or money");
		return true;
	}
}
