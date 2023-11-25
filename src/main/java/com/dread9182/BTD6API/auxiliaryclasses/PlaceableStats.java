package com.dread9182.BTD6API.auxiliaryclasses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PlaceableStats {
	
	@Getter
	@Setter
	private String damage;
	
	@Getter
	@Setter
	private String pierce;
	
	@Getter
	@Setter
	private String attackSpeed;
	
	@Getter
	@Setter
	private String range;
	
	@Getter
	@Setter
	private String type;
	
	@Getter
	@Setter
	private List<String> special;
}
