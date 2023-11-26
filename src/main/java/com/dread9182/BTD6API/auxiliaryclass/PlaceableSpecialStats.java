package com.dread9182.BTD6API.auxiliaryclass;

import lombok.Getter;
import lombok.Setter;

// Class for defining any special stats or conditions a hero or a tower can have
public class PlaceableSpecialStats {
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String value;
}
