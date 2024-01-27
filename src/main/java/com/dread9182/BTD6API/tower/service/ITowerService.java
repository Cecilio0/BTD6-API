package com.dread9182.BTD6API.tower.service;

import com.dread9182.BTD6API.tower.model.Tower;

import java.util.List;

public interface ITowerService {
	
	List<Tower> findAll();
	
	Tower findById(String id);
	
	Tower findByName(String name);
	
	List<Tower> findByType(String type);
	
	Tower update(String id, Tower tower);

}
