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
		return hr.findByName(name).orElse(null);
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
	
	@Override
	public Hero update(String id, Hero hero) {
		
		
		Hero toUpdate = hr.findById(id).orElse(null);
		
		if(toUpdate != null){
			toUpdate.setName(hero.getName()); // can't be null
			toUpdate.setDescription(hero.getDescription()); // can't be null
			toUpdate.setSkinChangeLevels(hero.getSkinChangeLevels()); // has to have at least length 4
			toUpdate.setSkins(hero.getSkins());
			toUpdate.setCost(hero.getCost()); // each cost has to be a positive integer
			toUpdate.setStats(hero.getStats()); // no field can be null
			toUpdate.setUnlock(hero.getUnlock()); // no field can be null
			toUpdate.setLevelSpeed(hero.getLevelSpeed()); // has to be a positive float
			// level field hast to be greater than 0 and lower than 21
			// description can't be null
			// rounds have to have integer values greater than or equal to 0
			// effects has to have at least length 1
			toUpdate.setLevels(hero.getLevels());
			
			hr.save(toUpdate);
		}
		
		
		return toUpdate;
	}
}
