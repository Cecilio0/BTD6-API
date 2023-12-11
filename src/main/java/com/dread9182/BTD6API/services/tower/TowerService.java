package com.dread9182.BTD6API.services.tower;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.models.Tower;
import com.dread9182.BTD6API.repositories.ITowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TowerService implements ITowerService{
	@Autowired
	private ITowerRepository tr;
	
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
		if(!Arrays.asList(validTowerTypes).contains(type))
			throw new ValueNotValidException("The type value has to be Primary, Military, Magic or Support");
		return tr.findByType(type);
	}
	
	@Override
	public Tower update(String id, Tower tower) {
		return null;
	}
}
