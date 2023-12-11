package com.dread9182.BTD6API.models;


import com.dread9182.BTD6API.auxiliaryclass.PlaceableCost;
import com.dread9182.BTD6API.auxiliaryclass.PlaceableStats;
import com.dread9182.BTD6API.auxiliaryclass.TowerPaths;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("towers")
public class Tower {
	@Id
	@Getter
	private String id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String description;
	
	@Getter
	@Setter
	private String type;
	
	@Getter
	@Setter
	private PlaceableCost cost;
	
	@Getter
	@Setter
	private PlaceableStats stats;
	
	@Getter
	@Setter
	private int footprint;
	
	@Getter
	@Setter
	private String defaultHotkey;
	
	@Getter
	@Setter
	private TowerPaths paths;
}
