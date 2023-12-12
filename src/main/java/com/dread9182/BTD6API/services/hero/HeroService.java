package com.dread9182.BTD6API.services.hero;

import com.dread9182.BTD6API.auxiliaryclass.HeroLevel;
import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.models.Hero;
import com.dread9182.BTD6API.repositories.IHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HeroService implements IHeroService{
	@Autowired
	private IHeroRepository hr;
	
	private final String[] validUnlockHow = {"level", "money"};
	
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
		if(!Arrays.asList(validUnlockHow).contains(how))
			throw new ValueNotValidException("The how value has to be either level or money");
		
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
		if (hero.getName() == null)
			throw new ValueNotValidException("The name value can not be null");
		
		if(hero.getDescription() == null)
			throw new ValueNotValidException("The description value can not be null");
		
		if(hero.getSkinChangeLevels() == null || hero.getSkinChangeLevels().size() < 4)
			throw new ValueNotValidException("The skinChangeLevels value has to have at least length 4");
		
		if(hero.getCost() == null
				|| hero.getCost().getEasy() < 0
				|| hero.getCost().getMedium() < 0
				|| hero.getCost().getHard() < 0
				|| hero.getCost().getImpoppable() < 0)
			throw new ValueNotValidException("Each cost value has to be a positive integer and can not be null");
		
		if(hero.getStats() == null
				|| hero.getStats().getType() == null
				|| hero.getStats().getDamage() == null
				|| hero.getStats().getRange() == null
				|| hero.getStats().getPierce() == null
				|| hero.getStats().getAttackSpeed() == null)
			throw new ValueNotValidException("No stats field aside from special can be null");
		
		if(hero.getUnlock() == null
				|| hero.getUnlock().getHow() == null
				|| hero.getUnlock().getValue() == null)
			throw new ValueNotValidException("No unlock field can be null");
		
		if(!Arrays.asList(validUnlockHow).contains(hero.getUnlock().getHow()))
			throw new ValueNotValidException("The how value has to be either level or money");
		
		if(hero.getLevelSpeed() == null)
			throw new ValueNotValidException("The levelSpeed value can not be null");
		
		if(hero.getLevels() == null)
			throw new ValueNotValidException("The levels value can not be null");
		
		for (HeroLevel heroLevel: hero.getLevels()) {
			if(heroLevel.getDescription() == null)
				throw new ValueNotValidException("The description value of each hero level can not be null");
			
			if(heroLevel.getLevel() < 1 || heroLevel.getLevel() > 20)
				throw new ValueNotValidException("The level value of each hero level has to be between 1 and 20");
			
			if(heroLevel.getEffects() == null
					|| heroLevel.getEffects().size() == 0)
				throw new ValueNotValidException("Each hero level must have at least one effect");
			
			if(heroLevel.getRounds() == null
					|| heroLevel.getRounds().getEasy() == null
					|| heroLevel.getRounds().getMedium() == null
					|| heroLevel.getRounds().getHard() == null
					|| heroLevel.getRounds().getImpoppable() == null)
				throw new ValueNotValidException("Each hero level round must have a value");
		}
			
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
}
