package com.dread9182.BTD6API.tower.model;

import com.dread9182.BTD6API.placeable.model.PlaceableCost;
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
