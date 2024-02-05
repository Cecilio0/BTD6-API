package com.dread9182.BTD6API.bloon.service;

import com.dread9182.BTD6API.bloon.model.Bloon;
import com.dread9182.BTD6API.bloon.IBloonRepository;
import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.hero.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BloonService implements IBloonService{
	@Autowired
	private final IBloonRepository br;
	
	
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
		return br.findByType(type);
	}
	
	@Override
	public Bloon update(String id, Bloon bloon) {
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
	
	@Override
	public Bloon save(Bloon bloon) {
		Bloon verifyUniqueness = br.findByName(bloon.getName()).orElse(null);
		if(verifyUniqueness != null)
			throw new ValueNotValidException("A bloon with this name already exists");
		
		return br
				.save(Bloon
						.builder()
						.name(bloon.getName())
						.type(bloon.getType())
						.rbe(bloon.getRbe())
						.hp(bloon.getHp())
						.speed(bloon.getSpeed())
						.children(bloon.getChildren())
						.firstRound(bloon.getFirstRound())
						.firstRoundABR(bloon.getFirstRoundABR())
						.immunities(bloon.getImmunities())
						.variants(bloon.getVariants())
						.build());
	}
}
