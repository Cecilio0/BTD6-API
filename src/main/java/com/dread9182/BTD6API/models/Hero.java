package com.dread9182.BTD6API.models;

import com.dread9182.BTD6API.auxiliaryclass.HeroLevel;
import com.dread9182.BTD6API.auxiliaryclass.HeroUnlock;
import com.dread9182.BTD6API.auxiliaryclass.PlaceableCost;
import com.dread9182.BTD6API.auxiliaryclass.PlaceableStats;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("heroes")
public class Hero {
	@Id
	private String id;
	
	private String name;
	
	private String description;
	
	private List<Integer> skinChangeLevels;
	
	private List<String> skins;
	
	private PlaceableCost cost;
	
	private PlaceableStats stats;
	
	private HeroUnlock unlock;
	
	private String levelSpeed;
	
	private List<HeroLevel> levels;
}
