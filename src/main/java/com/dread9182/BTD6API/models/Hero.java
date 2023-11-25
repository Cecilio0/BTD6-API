package com.dread9182.BTD6API.models;

import com.dread9182.BTD6API.auxiliaryclasses.HeroLevel;
import com.dread9182.BTD6API.auxiliaryclasses.HeroUnlock;
import com.dread9182.BTD6API.auxiliaryclasses.PlaceableCost;
import com.dread9182.BTD6API.auxiliaryclasses.PlaceableStats;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("heroes")
public class Hero {
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
	private List<Integer> skinChangeLevels;
	
	@Getter
	@Setter
	private List<String> skins;
	
	@Getter
	@Setter
	private PlaceableCost cost;
	
	@Getter
	@Setter
	private PlaceableStats stats;
	
	@Getter
	@Setter
	private HeroUnlock unlock;
	
	@Getter
	@Setter
	private String levelSpeed;
	
	@Getter
	@Setter
	private List<HeroLevel> levels;
}
