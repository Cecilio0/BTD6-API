package com.dread9182.BTD6API.auxiliaryclass;

import lombok.*;

// Class for defining any special stats or conditions a hero or a tower can have
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceableSpecialStats {
	
	private String name;
	
	private String value;
}
