package com.dread9182.BTD6API.services.tower;

import com.dread9182.BTD6API.models.Tower;

import java.util.List;

public interface ITowerService {
	
	List<Tower> findAll();
	
	Tower findById(String id);
	
	Tower findByName(String name);
	
	List<Tower> findByType(String type);
	
	Tower update(String id, Tower tower);

}
