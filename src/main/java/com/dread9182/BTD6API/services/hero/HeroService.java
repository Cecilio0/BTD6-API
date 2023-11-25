package com.dread9182.BTD6API.services.hero;

import com.dread9182.BTD6API.models.Hero;
import com.dread9182.BTD6API.repositories.IHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeroService implements IHeroService{
	@Autowired
	IHeroRepository hr;
	
	@Override
	public List<Hero> findAll() {
		return hr.findAll();
	}
	
	@Override
	public Hero findById(String id) {
		return hr.findById(id).orElse(null);
	}
	
	@Override
	public Hero findByName(String name) {
		return hr.findByName(name);
	}
	
	@Override
	public List<Hero> findByHowIsUnlocked(String how) {
		List<Hero> heroes = hr.findAll();
		List<Hero> responseHeroes = new ArrayList<>();
		
		for (Hero hero: heroes) {
			if (hero.getUnlock().getHow().equalsIgnoreCase(how))
				responseHeroes.add(hero);
		}
		
		return responseHeroes;
	}
}
