package com.dread9182.BTD6API.interceptor.map;

import com.dread9182.BTD6API.bloon.model.BloonTypes;
import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.map.model.MapDifficulties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

public class MapDifficultyRequestHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String difficulty = uri.split("/")[3];
		
		if(Arrays.stream(MapDifficulties.values()).noneMatch((validDifficulty) ->
				validDifficulty.name().equals(difficulty)))
			throw new ValueNotValidException("The difficulty value has to be Beginner, Intermediate, Advanced or Expert");
		
		return true;
	}
}
