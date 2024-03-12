package com.dread9182.BTD6API.tower.service;

import com.dread9182.BTD6API.tower.model.Tower;
import com.dread9182.BTD6API.tower.model.request.TowerSaveRequest;
import com.dread9182.BTD6API.tower.model.request.TowerUpdateRequest;

import java.util.List;

public interface ITowerService {
	
	List<Tower> findAll();
	
	Tower findById(String id);
	
	Tower findByName(String name);
	
	List<Tower> findByType(String type);
	
	Tower update(String id, TowerUpdateRequest tower);
	
	Tower save(TowerSaveRequest tower);

}
