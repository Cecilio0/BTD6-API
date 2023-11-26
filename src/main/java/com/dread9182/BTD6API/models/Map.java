package com.dread9182.BTD6API.models;

import com.dread9182.BTD6API.auxiliaryclass.MapTrack;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("maps")
public class Map {
	@Id
	@Getter
	private String id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String difficulty;
	
	@Getter
	@Setter
	private List<MapTrack> tracks;
}
