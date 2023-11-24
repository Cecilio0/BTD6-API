package com.dread9182.BTD6API.services.map;

import com.dread9182.BTD6API.models.Map;

import java.util.List;

public interface IMapService {
	
	List<Map> findAll();
	
	Map findById(String id);
	
	Map findByName(String name);
	
	List<Map> findByDifficulty(String difficulty);
	
	Map update(String id, Map map);
	
}
