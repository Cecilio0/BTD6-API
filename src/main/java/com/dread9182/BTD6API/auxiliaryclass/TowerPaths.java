package com.dread9182.BTD6API.auxiliaryclass;

import lombok.*;

import java.util.List;
import java.util.Optional;
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
