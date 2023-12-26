package com.dread9182.BTD6API.auxiliaryclass;

import lombok.*;

import java.util.List;

// Class for defining the effects of a specific level for a hero
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroLevel {
	
	private int level;
	
	private String description;
	
	private HeroLevelUpRounds rounds;
	
	private List<String> effects;
}
