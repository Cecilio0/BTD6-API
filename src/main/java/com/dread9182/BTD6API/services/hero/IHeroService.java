package com.dread9182.BTD6API.services.hero;

import com.dread9182.BTD6API.models.Hero;

import java.util.List;

public interface IHeroService {
	
	List<Hero> findAll();
	
	Hero findById(String id);
	
	Hero findByName(String name);
	
	List<Hero> findByHowIsUnlocked(String how);
	
}
