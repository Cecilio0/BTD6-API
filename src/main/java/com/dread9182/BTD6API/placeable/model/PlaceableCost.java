package com.dread9182.BTD6API.placeable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Class for defining explicit costs for each difficult for any placeable object be it a Hero or a tower
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceableCost {

	private int easy;
	
	private int medium;
	
	private int hard;
	
	private int impoppable;
}
