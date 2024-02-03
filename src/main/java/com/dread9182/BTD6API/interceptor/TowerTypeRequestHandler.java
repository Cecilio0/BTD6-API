package com.dread9182.BTD6API.interceptor;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.tower.model.Tower;
import com.dread9182.BTD6API.tower.model.TowerTypes;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class TowerTypeRequestHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String type = uri.split("/")[3];
		log.info(type);
		
		if(Arrays.stream(TowerTypes.values()).noneMatch((validType) -> type.equals(validType.name())))
			throw new ValueNotValidException("The type value has to be Primary, Military, Magic or Support");
		return true;
	}
}
