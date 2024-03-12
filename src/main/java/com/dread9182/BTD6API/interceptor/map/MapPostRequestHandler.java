package com.dread9182.BTD6API.interceptor.map;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.map.model.MapDifficulties;
import com.dread9182.BTD6API.map.model.MapTrack;
import com.dread9182.BTD6API.map.model.request.MapSaveRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapPostRequestHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String bodyString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		MapSaveRequest map = new ObjectMapper().readValue(bodyString, MapSaveRequest.class);
		
		if(map.getName() == null || map.getName().equals(""))
			throw new ValueNotValidException("The name value can not be null");
		
		if(Arrays.stream(MapDifficulties.values()).noneMatch((validDifficulty) ->
				validDifficulty.name().equals(map.getDifficulty())))
			throw new ValueNotValidException("The difficulty value has to be Beginner, Intermediate, Advanced or Expert");
		
		List<MapTrack> tracks = map.getTracks();
		if(tracks == null || tracks.size() == 0)
			throw new ValueNotValidException("The map must have at least one track");
		
		for (MapTrack track: tracks) {
			if(track.getName() == null)
				throw new ValueNotValidException("The name value for a track can not be null");
			if (track.getRbs() <= 0)
				throw new ValueNotValidException("The track length in red bloon seconds (rbs) has to be greater than 0");
		}
		
		return true;
	}
}
