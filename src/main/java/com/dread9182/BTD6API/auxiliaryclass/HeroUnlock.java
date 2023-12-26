package com.dread9182.BTD6API.auxiliaryclass;

import lombok.*;

// Class for defining how a hero is unlocked and how much it costs or at which level it happens
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroUnlock {
	
	private String how;
	
	private String value;
}
