package com.dread9182.BTD6API.interceptor.tower;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.tower.model.Tower;
import com.dread9182.BTD6API.tower.model.TowerTypes;
import com.dread9182.BTD6API.tower.model.TowerUpgrade;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TowerBodyRequestHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String bodyString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		Tower tower = new ObjectMapper().readValue(bodyString, Tower.class);
		
		if (tower.getName() == null || tower.getName().equals(""))
			throw new ValueNotValidException("The name value can not be null");
		
		if (tower.getDescription() == null || tower.getDescription().equals(""))
			throw new ValueNotValidException("The description value can not be null");
		
		if(Arrays.stream(TowerTypes.values()).noneMatch((type) -> tower.getType().equals(type.name())))
			throw new ValueNotValidException("The type value has to be Primary, Military, Magic or Support");
		
		if (tower.getCost() == null
				|| tower.getCost().getEasy() < 0
				|| tower.getCost().getMedium() < 0
				|| tower.getCost().getHard() < 0
				|| tower.getCost().getImpoppable() < 0)
			throw new ValueNotValidException("Each cost value has to be a positive integer and can not be null");
		
		if (tower.getStats() == null
				|| tower.getStats().getType() == null || tower.getStats().getType().equals("")
				|| tower.getStats().getDamage() == null || tower.getStats().getDamage().equals("")
				|| tower.getStats().getRange() == null || tower.getStats().getRange().equals("")
				|| tower.getStats().getPierce() == null || tower.getStats().getPierce().equals("")
				|| tower.getStats().getAttackSpeed() == null || tower.getStats().getAttackSpeed().equals(""))
			throw new ValueNotValidException("No stats field aside from special can be null");
		
		if (tower.getFootprint() < 0)
			throw new ValueNotValidException("The footprint value has to be greater than 0");
		
		if (tower.getDefaultHotkey() == null  || tower.getDefaultHotkey().equals(""))
			throw new ValueNotValidException("The defaultHotkey value can not be null");
		
		if (tower.getPaths() == null)
			throw new ValueNotValidException("The paths value can not be null");
		
		validateTowerUpgrades(tower.getPaths().getPath1());
		validateTowerUpgrades(tower.getPaths().getPath2());
		validateTowerUpgrades(tower.getPaths().getPath3());
		
		return true;
	}
	
	private void validateTowerUpgrades (List<TowerUpgrade> upgrades) {
		for(TowerUpgrade upgrade: upgrades){
			if (upgrade.getName() == null || upgrade.getName().equals(""))
				throw new ValueNotValidException("The name value for an upgrade can not be null");
			
			if (upgrade.getDescription() == null || upgrade.getDescription().equals(""))
				throw new ValueNotValidException("The description value of an upgrade can not be null");
			
			if (upgrade.getCost() == null
					|| upgrade.getCost().getEasy() < 0
					|| upgrade.getCost().getMedium() < 0
					|| upgrade.getCost().getHard() < 0
					|| upgrade.getCost().getImpoppable() < 0)
				throw new ValueNotValidException("Each cost value for an upgrade has to be a positive integer and can not be null");
			
			if (upgrade.getUnlockXp() < 0)
				throw new ValueNotValidException("The unlockXp value for an upgrade has to be greater than zero");
			
			if (upgrade.getEffects() == null
					|| upgrade.getEffects().size() == 0)
				throw new ValueNotValidException("Each tower upgrade must have at least one effect");
		}
		
	}
}
