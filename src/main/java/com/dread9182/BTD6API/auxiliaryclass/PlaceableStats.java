package com.dread9182.BTD6API.auxiliaryclass;

import lombok.*;

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
	
	@Getter
	@Setter
	private String type;
	
	@Getter
	@Setter
	private List<PlaceableSpecialStats> special;
}
