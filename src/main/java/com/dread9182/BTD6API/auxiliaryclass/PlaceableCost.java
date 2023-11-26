package com.dread9182.BTD6API.auxiliaryclass;

import lombok.Getter;
import lombok.Setter;

// Class for defining explicit costs for each difficult for any placeable object be it a Hero or a tower
public class PlaceableCost {

	@Getter
	@Setter
	private int easy;
	
	@Getter
	@Setter
	private int medium;
	
	@Getter
	@Setter
	private int hard;
	
	@Getter
	@Setter
	private int impoppable;
}
