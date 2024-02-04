package com.dread9182.BTD6API.hero.service;

import com.dread9182.BTD6API.hero.model.HeroLevel;
import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.hero.model.Hero;
import com.dread9182.BTD6API.hero.IHeroRepository;
import com.dread9182.BTD6API.tower.model.Tower;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HeroService implements IHeroService {
	@Autowired
	private final IHeroRepository hr;
	
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
			toUpdate.setName(hero.getName());
			toUpdate.setDescription(hero.getDescription());
			toUpdate.setSkinChangeLevels(hero.getSkinChangeLevels());
			toUpdate.setSkins(hero.getSkins());
			toUpdate.setCost(hero.getCost());
			toUpdate.setStats(hero.getStats());
			toUpdate.setUnlock(hero.getUnlock());
			toUpdate.setLevelSpeed(hero.getLevelSpeed());
			toUpdate.setLevels(hero.getLevels());
			
			hr.save(toUpdate);
		}
		
		return toUpdate;
	}
	
	@Override
	public Hero save(Hero hero) {
		Hero verifyUniqueness = hr.findByName(hero.getName()).orElse(null);
		if(verifyUniqueness != null)
			throw new ValueNotValidException("A hero with this name already exists");
		
		return hr
				.save(Hero
						.builder()
						.name(hero.getName())
						.description(hero.getDescription())
						.skinChangeLevels(hero.getSkinChangeLevels())
						.skins(hero.getSkins())
						.cost(hero.getCost())
						.stats(hero.getStats())
						.unlock(hero.getUnlock())
						.levelSpeed(hero.getLevelSpeed())
						.levels(hero.getLevels())
						.build());
	}
}
