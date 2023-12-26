package com.dread9182.BTD6API.auxiliaryclass;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TowerUpgrade {
	
	private String name;
	
	private String description;
	
	private PlaceableCost cost;
	
	private int unlockXp;
	
	private List<String> effects;
	
}
