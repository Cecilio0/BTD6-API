package com.dread9182.BTD6API.map.service;

import com.dread9182.BTD6API.exception.ValueNotValidException;
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
	
	// todo create enum with this information
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
		// todo implement following code as interceptor
		if(!Arrays.asList(validMapDifficulties).contains(difficulty))
			throw new ValueNotValidException("The difficulty value has to be Beginner, Intermediate, Advanced or Expert");
		return mr.findByDifficulty(difficulty);
	}
	
	@Override
	public Map update(String id, Map map) {
		// todo implement following code as interceptor
		if(map.getName() == null)
			throw new ValueNotValidException("The name value can not be null");
		
		if(!Arrays.asList(validMapDifficulties).contains(map.getDifficulty()))
			throw new ValueNotValidException("The difficulty value has to be Beginner, Intermediate, Advanced or Expert");
			
		List<MapTrack> tracks = map.getTracks();
		if(tracks == null || tracks.size() == 0)
			throw new ValueNotValidException("The map must have at least one track");
		
		for (MapTrack track: tracks) {
			if(track.getName() == null)
				throw new ValueNotValidException("The name value for a track can not be null");
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
