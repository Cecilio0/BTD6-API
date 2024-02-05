package com.dread9182.BTD6API.interceptor.bloon;

import com.dread9182.BTD6API.bloon.model.Bloon;
import com.dread9182.BTD6API.bloon.model.BloonTypes;
import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BloonBodyRequestHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String bodyString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		Bloon bloon = new ObjectMapper().readValue(bodyString, Bloon.class);
		
		if(bloon.getName() == null || bloon.getName().equals(""))
			throw new ValueNotValidException("The name value can not be null");
		
		if(Arrays.stream(BloonTypes.values()).noneMatch((type) -> bloon.getType().equals(type.name())))
			throw new ValueNotValidException("The type value has to be either bloon or moab");
		
		if(bloon.getRbe() < 0)
			throw new ValueNotValidException("The rbe value has to greater than or equal to 0");
		
		if(bloon.getHp() < 0)
			throw new ValueNotValidException("The hp value has to be greater than or equal to 0");
		
		if(bloon.getSpeed() < 0)
			throw new ValueNotValidException("The speed value has to be greater than or equal to 0");
		
		if(bloon.getFirstRound() < 0)
			throw new ValueNotValidException("The firstRound value has to be greater than or equal to 0");
		
		if(bloon.getFirstRoundABR() < 0)
			throw new ValueNotValidException("The firstRoundABR value has to be greater than or equal to 0");
		
		return true;
	}
}
