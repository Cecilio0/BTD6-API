package com.dread9182.BTD6API.tower.model;


import com.dread9182.BTD6API.placeable.model.PlaceableCost;
import com.dread9182.BTD6API.placeable.model.PlaceableStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("towers")
public class Tower {
	@Id
	private String id;
	
	private String name;
	
	private String description;
	
	private String type;
	
	private PlaceableCost cost;
	
	private PlaceableStats stats;
	
	private int footprint;
	
	private String defaultHotkey;
	
	private TowerPaths paths;
}
