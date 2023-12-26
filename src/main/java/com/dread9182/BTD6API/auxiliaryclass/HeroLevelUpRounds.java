package com.dread9182.BTD6API.auxiliaryclass;

import lombok.*;

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
