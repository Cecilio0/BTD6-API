package com.dread9182.BTD6API.map.service;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.hero.model.Hero;
import com.dread9182.BTD6API.map.IMapRepository;
import com.dread9182.BTD6API.map.model.Map;
import com.dread9182.BTD6API.map.model.MapTrack;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapService implements IMapService{
	@Autowired
	private final IMapRepository mr;
	
	@Override
	public List<Map> findAll() {
		return mr.findAll();
	}
	
	@Override
	public Map findById(String id) {
		return mr.findById(id).orElse(null);
	}
	
	@Override
	public Map findByName(String name) {
		return mr.findByName(name).orElse(null);
	}
	
	@Override
	public List<Map> findByDifficulty(String difficulty) {
		return mr.findByDifficulty(difficulty);
	}
	
	@Override
	public Map update(String id, Map map) {
		Map toUpdate = mr.findById(id).orElse(null);
		
		if(toUpdate != null){
			toUpdate.setName(map.getName());
			toUpdate.setDifficulty(map.getDifficulty());
			toUpdate.setTracks(map.getTracks());
			
			mr.save(toUpdate);
		}
		
		return toUpdate;
	}
	
	@Override
	public Map save(Map map) {
		Map verifyUniqueness = mr.findByName(map.getName()).orElse(null);
		if(verifyUniqueness != null)
			throw new ValueNotValidException("A map with this name already exists");
		
		return mr
				.save(Map
						.builder()
						.name(map.getName())
						.difficulty(map.getDifficulty())
						.tracks(map.getTracks())
						.build());
	}
}
