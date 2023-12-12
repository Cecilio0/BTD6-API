package com.dread9182.BTD6API.services.bloon;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.models.Bloon;
import com.dread9182.BTD6API.repositories.IBloonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BloonService implements IBloonService{
	@Autowired
	private IBloonRepository br;
	
	private final String[] validBloonTypes = {"bloon", "moab"};
	
	@Override
	public List<Bloon> findAll() {
		return br.findAll();
	}
	
	@Override
	public Bloon findById(String id) {
		return br.findById(id).orElse(null);
	}
	
	@Override
	public Bloon findByName(String name) {
		return br.findByName(name).orElse(null);
	}
	
	@Override
	public List<Bloon> findByType(String type) {
		if(!Arrays.asList(validBloonTypes).contains(type))
			throw new ValueNotValidException("The type value has to be either moab or bloon");
		return br.findByType(type);
	}
	
	@Override
	public Bloon update(String id, Bloon bloon) {
		
		if(bloon.getName() == null)
			throw new ValueNotValidException("The name value can not be null");
		
		if(!Arrays.asList(validBloonTypes).contains(bloon.getType()))
			throw new ValueNotValidException("The type value has to be either moab or bloon");
		
		if(bloon.getRbe() < 0)
			throw new ValueNotValidException("The rbe value has to greater than or equal to 0");
		
		if(bloon.getHp() < 0)
			throw new ValueNotValidException("The hp value has to be greater than or equal to 0");
		
		if(bloon.getSpeed() < 0)
			throw new ValueNotValidException("The speed value has to be greater than or equal to 0");
		
		if(bloon.getFirstRound() < 0)
			throw new ValueNotValidException("The firstRound value has to be greater than or equal to 0");
		
		if(bloon.getFirstRoundABR() < 0)
			throw new ValueNotValidException("The firstRoundABR value has to be greater than or equal to 0");
		
		Bloon toUpdate = br.findById(id).orElse(null);
		
		if(toUpdate != null){
			toUpdate.setName(bloon.getName());
			toUpdate.setType(bloon.getType());
			toUpdate.setRbe(bloon.getRbe());
			toUpdate.setHp(bloon.getHp());
			toUpdate.setSpeed(bloon.getSpeed());
			toUpdate.setChildren(bloon.getChildren());
			toUpdate.setFirstRound(bloon.getFirstRound());
			toUpdate.setFirstRoundABR(bloon.getFirstRoundABR());
			toUpdate.setImmunities(bloon.getImmunities());
			toUpdate.setVariants(bloon.getVariants());
			
			br.save(toUpdate);
		}
		return toUpdate;
	}
}
