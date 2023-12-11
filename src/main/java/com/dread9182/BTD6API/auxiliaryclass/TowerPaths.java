package com.dread9182.BTD6API.auxiliaryclass;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

public class TowerPaths {
	
	@Getter
	@Setter
	private List<TowerUpgrade> path1;
	
	@Getter
	@Setter
	private List<TowerUpgrade> path2;
	
	@Getter
	@Setter
	private List<TowerUpgrade> path3;
	
	@Getter
	@Setter
	private TowerUpgrade paragon;
	
}
