package com.dread9182.BTD6API.auxiliaryclass;

import lombok.Getter;
import lombok.Setter;

// Class for defining how a hero is unlocked and how much it costs or at which level it happens
public class HeroUnlock {
	@Getter
	@Setter
	private String how;
	
	@Getter
	@Setter
	private String value;
}
