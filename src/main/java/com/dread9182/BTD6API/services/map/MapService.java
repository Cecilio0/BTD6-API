package com.dread9182.BTD6API.services.map;

import com.dread9182.BTD6API.auxiliaryclass.MapTrack;
import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.models.Map;
import com.dread9182.BTD6API.repositories.IMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MapService implements IMapService{
	@Autowired
	private IMapRepository mr;
	
	private final String[] validMapDifficulties = {"Beginner", "Intermediate", "Advanced", "Expert"};
	
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
		if(!Arrays.asList(validMapDifficulties).contains(difficulty))
			throw new ValueNotValidException("The difficulty value has to be Beginner, Intermediate, Advanced or Expert");
		return mr.findByDifficulty(difficulty);
	}
	
	@Override
	public Map update(String id, Map map) {
		
		if(!Arrays.asList(validMapDifficulties).contains(map.getDifficulty()))
			throw new ValueNotValidException("The difficulty value has to be Beginner, Intermediate, Advanced or Expert");
			
		List<MapTrack> tracks = map.getTracks();
		if(tracks == null || tracks.size() == 0)
			throw new ValueNotValidException("The map must have at least one track");
		
		for (MapTrack track: tracks) {
			if (track.getRbs() <= 0)
				throw new ValueNotValidException("The track length in red bloon seconds (rbs) has to be greater than 0");
		}
			
		Map toUpdate = mr.findById(id).orElse(null);
		
		if(toUpdate != null){
			toUpdate.setName(map.getName());
			toUpdate.setDifficulty(map.getDifficulty());
			toUpdate.setTracks(map.getTracks());
			
			mr.save(toUpdate);
		}
		
		return toUpdate;
	}
}
