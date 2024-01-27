package com.dread9182.BTD6API.tower.service;

import com.dread9182.BTD6API.tower.model.TowerUpgrade;
import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.tower.ITowerRepository;
import com.dread9182.BTD6API.tower.model.Tower;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TowerService implements ITowerService{
	@Autowired
	private final ITowerRepository tr;
	
	// todo create enum with this information
	private final String[] validTowerTypes = {"Primary", "Military", "Magic", "Support"};
	
	@Override
	public List<Tower> findAll() {
		return tr.findAll();
	}
	
	@Override
	public Tower findById(String id) {
		return tr.findById(id).orElse(null);
	}
	
	@Override
	public Tower findByName(String name) {
		return tr.findByName(name).orElse(null);
	}
	
	@Override
	public List<Tower> findByType(String type) {
		// todo implement following code as interceptor
		if(!Arrays.asList(validTowerTypes).contains(type))
			throw new ValueNotValidException("The type value has to be Primary, Military, Magic or Support");
		
		return tr.findByType(type);
	}
	
	@Override
	public Tower update(String id, Tower tower) {
		// todo implement following code as interceptor
		if (tower.getName() == null)
			throw new ValueNotValidException("The name value can not be null");
		
		if (tower.getDescription() == null)
			throw new ValueNotValidException("The description value can not be null");
		
		if (!Arrays.asList(validTowerTypes).contains(tower.getType()))
			throw new ValueNotValidException("The type value has to be Primary, Military, Magic or Support");
		
		if (tower.getCost() == null
				|| tower.getCost().getEasy() < 0
				|| tower.getCost().getMedium() < 0
				|| tower.getCost().getHard() < 0
				|| tower.getCost().getImpoppable() < 0)
			throw new ValueNotValidException("Each cost value has to be a positive integer and can not be null");
		
		if (tower.getStats() == null
				|| tower.getStats().getType() == null
				|| tower.getStats().getDamage() == null
				|| tower.getStats().getRange() == null
				|| tower.getStats().getPierce() == null
				|| tower.getStats().getAttackSpeed() == null)
			throw new ValueNotValidException("No stats field aside from special can be null");
		
		if (tower.getFootprint() < 0)
			throw new ValueNotValidException("The footprint value has to be greater than 0");
		
		if (tower.getDefaultHotkey() == null)
			throw new ValueNotValidException("The defaultHotkey value can not be null");
		
		if (tower.getPaths() == null)
			throw new ValueNotValidException("The paths value can not be null");
		
		validateTowerUpgrades(tower.getPaths().getPath1());
		validateTowerUpgrades(tower.getPaths().getPath2());
		validateTowerUpgrades(tower.getPaths().getPath3());
		
		
		Tower toUpdate = tr.findById(id).orElse(null);
		
		if (toUpdate != null) {
			toUpdate.setName(tower.getName());
			toUpdate.setDescription(tower.getDescription());
			toUpdate.setType(tower.getType());
			toUpdate.setCost(tower.getCost());
			toUpdate.setStats(tower.getStats());
			toUpdate.setFootprint(tower.getFootprint());
			toUpdate.setDefaultHotkey(tower.getDefaultHotkey());
			toUpdate.setPaths(tower.getPaths());
			
			tr.save(toUpdate);
		}
		
		return toUpdate;
	}
	
	private void validateTowerUpgrades (List <TowerUpgrade> upgrades) {
		for(TowerUpgrade upgrade: upgrades){
			if (upgrade.getName() == null)
				throw new ValueNotValidException("The name value fo an upgrade can not be null");
			
			if (upgrade.getDescription() == null)
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
