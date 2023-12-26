package com.dread9182.BTD6API.auxiliaryclass;

import lombok.*;

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
