package com.dread9182.BTD6API.services.hero;

import com.dread9182.BTD6API.auxiliaryclass.HeroLevel;
import com.dread9182.BTD6API.exception.ValueNotValidException;
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
				|| hero.getStats().getAttackSpeed() == null
				|| hero.getStats().getSpecial() == null)
			throw new ValueNotValidException("No stats field can be null");
		
		if(hero.getUnlock() == null
				|| hero.getUnlock().getHow() == null
				|| hero.getUnlock().getValue() == null)
			throw new ValueNotValidException("No unlock field can be null");
		
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
