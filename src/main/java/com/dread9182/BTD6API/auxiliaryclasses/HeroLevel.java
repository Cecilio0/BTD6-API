package com.dread9182.BTD6API.auxiliaryclasses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

// Class for defining the effects of a specific level for a hero
public class HeroLevel {
	
	@Getter
	@Setter
	private int level;
	
	@Getter
	@Setter
	private String description;
	
	@Getter
	@Setter
	private HeroLevelUpRounds rounds;
	
	@Getter
	@Setter
	private List<String> effects;
}
