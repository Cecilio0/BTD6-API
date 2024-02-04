package com.dread9182.BTD6API.hero.service;

import com.dread9182.BTD6API.hero.model.Hero;

import java.util.List;

public interface IHeroService {
	
	List<Hero> findAll();
	
	Hero findById(String id);
	
	Hero findByName(String name);
	
	List<Hero> findByHowIsUnlocked(String how);
	
	Hero update(String id, Hero hero);
	
	Hero save(Hero hero);
	
}
