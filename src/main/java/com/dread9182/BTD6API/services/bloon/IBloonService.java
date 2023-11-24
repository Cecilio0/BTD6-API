package com.dread9182.BTD6API.services.bloon;

import com.dread9182.BTD6API.models.Bloon;

import java.util.List;

public interface IBloonService {
	List<Bloon> findAll();
	
	Bloon findById(String id);
	
	Bloon findByName(String name);
	
	List<Bloon> findByType(String type);
	
	Bloon update(String id, Bloon bloon);
}
