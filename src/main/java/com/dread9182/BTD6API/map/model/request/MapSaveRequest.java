package com.dread9182.BTD6API.map.model.request;

import com.dread9182.BTD6API.map.model.MapTrack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapSaveRequest {
	private String name;
	
	private String difficulty;
	
	private List<MapTrack> tracks;
}
