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
		return tr.findByType(type);
	}
	
	@Override
	public Tower update(String id, Tower tower) {
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
}
