package com.dread9182.BTD6API.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("bloons")
public class Bloon {
	
	@Id
	@Getter
	private String id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String type;
	
	@Getter
	@Setter
	private int rbe;
	
	@Getter
	@Setter
	private int hp;
	
	@Getter
	@Setter
	private int speed;
	
	@Getter
	@Setter
	private List<String> children;
	
	@Getter
	@Setter
	private int firstRound;
	
	@Getter
	@Setter
	private int firstRoundABR;
	
	@Getter
	@Setter
	private List<String> immunities;
	
	@Getter
	@Setter
	private List<String> variants;
}
