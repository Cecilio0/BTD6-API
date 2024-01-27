package com.dread9182.BTD6API.placeable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceableStats {
	
	private String damage;
	
	private String pierce;
	
	private String attackSpeed;
	
	private String range;
	
	private String type;
	
	private List<PlaceableSpecialStats> special;
}
