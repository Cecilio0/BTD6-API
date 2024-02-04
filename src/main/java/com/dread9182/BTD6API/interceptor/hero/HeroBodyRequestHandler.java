package com.dread9182.BTD6API.interceptor.hero;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.hero.model.HeroLevel;
import com.dread9182.BTD6API.hero.model.Hero;
import com.dread9182.BTD6API.hero.model.HeroUnlockHow;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HeroBodyRequestHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String bodyString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		Hero hero = new ObjectMapper().readValue(bodyString, Hero.class);
		
		if (hero.getName() == null || hero.getName().equals(""))
			throw new ValueNotValidException("The name value can not be null");
		
		if (hero.getDescription() == null || hero.getDescription().equals(""))
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
		
		if(Arrays.stream(HeroUnlockHow.values()).noneMatch((type) -> hero.getUnlock().getHow().equals(type.name())))
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
		
		return true;
	}
}
