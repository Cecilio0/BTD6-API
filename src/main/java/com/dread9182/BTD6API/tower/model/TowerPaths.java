package com.dread9182.BTD6API.tower.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TowerPaths {
	
	private List<TowerUpgrade> path1;
	
	private List<TowerUpgrade> path2;
	
	private List<TowerUpgrade> path3;
	
	private TowerUpgrade paragon;
	
}
