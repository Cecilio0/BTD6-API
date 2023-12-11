package com.dread9182.BTD6API.auxiliaryclass;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TowerUpgrade {
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String description;
	
	@Getter
	@Setter
	private PlaceableCost cost;
	
	@Getter
	@Setter
	private int unlockXp;
	
	@Getter
	@Setter
	private List<String> effects;
	
}
