package com.dread9182.BTD6API.hero.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Class for defining at which round a hero will reach a specific level, given he was placed at round 1
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroLevelUpRounds {
	
	private String easy;
	
	private String medium;
	
	private String hard;
	
	private String impoppable;
	
}
