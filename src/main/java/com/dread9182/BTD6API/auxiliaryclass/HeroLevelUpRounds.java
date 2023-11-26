package com.dread9182.BTD6API.auxiliaryclass;

import lombok.Getter;
import lombok.Setter;

// Class for defining at which round a hero will reach a specific level, given he was placed at round 1
public class HeroLevelUpRounds {
	@Getter
	@Setter
	private String easy;
	
	@Getter
	@Setter
	private String medium;
	
	@Getter
	@Setter
	private String hard;
	
	@Getter
	@Setter
	private String impoppable;
	
}
