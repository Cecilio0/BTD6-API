package com.dread9182.BTD6API.tower.model.request;

import com.dread9182.BTD6API.placeable.model.PlaceableCost;
import com.dread9182.BTD6API.placeable.model.PlaceableStats;
import com.dread9182.BTD6API.tower.model.TowerPaths;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TowerUpdateRequest {
	
	private String description;
	
	private PlaceableCost cost;
	
	private PlaceableStats stats;
	
	private int footprint;
	
	private String defaultHotkey;
	
	private TowerPaths paths;
}
