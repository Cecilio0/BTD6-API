package com.dread9182.BTD6API.map.service;

import com.dread9182.BTD6API.map.model.Map;
import com.dread9182.BTD6API.map.model.request.MapSaveRequest;
import com.dread9182.BTD6API.map.model.request.MapUpdateRequest;

import java.util.List;

public interface IMapService {
	
	List<Map> findAll();
	
	Map findById(String id);
	
	Map findByName(String name);
	
	List<Map> findByDifficulty(String difficulty);
	
	Map update(String id, MapUpdateRequest map);
	
	Map save(MapSaveRequest map);
	
}
