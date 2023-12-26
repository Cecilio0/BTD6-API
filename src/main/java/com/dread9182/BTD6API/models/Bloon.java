package com.dread9182.BTD6API.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("bloons")
public class Bloon {
	
	@Id
	private String id;
	
	private String name;
	
	private String type;
	
	private int rbe;
	
	private int hp;
	
	private int speed;
	
	private List<String> children;
	
	private int firstRound;
	
	private int firstRoundABR;
	
	private List<String> immunities;
	
	private List<String> variants;
}
