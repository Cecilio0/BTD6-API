package com.dread9182.BTD6API.map.service;

import com.dread9182.BTD6API.map.model.Map;

import java.util.List;

public interface IMapService {
	
	List<Map> findAll();
	
	Map findById(String id);
	
	Map findByName(String name);
	
	List<Map> findByDifficulty(String difficulty);
	
	Map update(String id, Map map);
	
	Map save(Map map);
	
}
