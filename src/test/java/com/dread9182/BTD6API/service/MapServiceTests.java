package com.dread9182.BTD6API.service;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.map.IMapRepository;
import com.dread9182.BTD6API.map.model.Map;
import com.dread9182.BTD6API.map.model.MapTrack;
import com.dread9182.BTD6API.map.model.request.MapSaveRequest;
import com.dread9182.BTD6API.map.model.request.MapUpdateRequest;
import com.dread9182.BTD6API.map.service.MapService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MapServiceTests {

	@InjectMocks
	private MapService ms;
	
	@Mock
	private IMapRepository mr;
	
	@Test
	public void MapService_FindAll_ReturnsMapList() {
		List<MapTrack> tracks = new ArrayList<>();
		
		tracks.add(MapTrack.builder().name("route 1").rbs(15).build());
		tracks.add(MapTrack.builder().name("route 2").rbs(30).build());
		
		List<Map> maps = new ArrayList<>();
		
		maps.add(Map.builder()
				.name("any_map1")
				.difficulty("Beginner")
				.tracks(tracks)
				.build()
		);
		
		maps.add(Map.builder()
				.name("any_map2")
				.difficulty("Intermediate")
				.tracks(tracks)
				.build()
		);
		
		Mockito.when(mr.findAll()).thenReturn(maps);
		
		List<Map> response = ms.findAll();
		Assertions.assertNotNull(response);
		Assertions.assertEquals(maps, response);
	}
	
	@Test
	public void MapService_FindById_ReturnsMap() {
		
		List<MapTrack> tracks = new ArrayList<>();
		
		tracks.add(MapTrack.builder().name("route 1").rbs(15).build());
		tracks.add(MapTrack.builder().name("route 2").rbs(30).build());
		
		Map map = Map.builder()
				.name("any_map1")
				.difficulty("Beginner")
				.tracks(tracks)
				.build();
		
		Mockito.when(mr.findById(Mockito.anyString())).thenReturn(Optional.of(map));
		
		Map response = ms.findById("any_id");
		Assertions.assertNotNull(response);
		Assertions.assertEquals(map, response);
	}
	
	@Test
	public void MapService_FindById_ReturnsNull() {
		Mockito.when(mr.findById(Mockito.anyString())).thenReturn(Optional.empty());
		
		Map response = ms.findById("any_id");
		Assertions.assertNull(response);
	}
	
	@Test
	public void MapService_FindByName_ReturnsMap() {
		
		List<MapTrack> tracks = new ArrayList<>();
		
		tracks.add(MapTrack.builder().name("route 1").rbs(15).build());
		tracks.add(MapTrack.builder().name("route 2").rbs(30).build());
		
		Map map = Map.builder()
				.name("any_map1")
				.difficulty("Beginner")
				.tracks(tracks)
				.build();
		
		Mockito.when(mr.findByName(Mockito.anyString())).thenReturn(Optional.of(map));
		
		Map response = ms.findByName("any_name");
		Assertions.assertNotNull(response);
		Assertions.assertEquals(map, response);
	}
	
	@Test
	public void MapService_FindByName_ReturnsNull() {
		Mockito.when(mr.findByName(Mockito.anyString())).thenReturn(Optional.empty());
		
		Map response = ms.findByName("any_name");
		Assertions.assertNull(response);
	}
	
	@Test
	public void MapService_FindByDifficulty_ReturnsMap() {
		
		List<MapTrack> tracks = new ArrayList<>();
		
		tracks.add(MapTrack.builder().name("route 1").rbs(15).build());
		tracks.add(MapTrack.builder().name("route 2").rbs(30).build());
		
		List<Map> maps = new ArrayList<>();
		
		maps.add(Map.builder()
				.name("any_map1")
				.difficulty("Beginner")
				.tracks(tracks)
				.build()
		);
		
		maps.add(Map.builder()
				.name("any_map2")
				.difficulty("Intermediate")
				.tracks(tracks)
				.build()
		);
		
		Mockito.when(mr.findByDifficulty(Mockito.anyString())).thenReturn(maps);
		
		List<Map> response = ms.findByDifficulty("Beginner");
		Assertions.assertNotNull(response);
		Assertions.assertEquals(maps, response);
	}
	
	@Test
	public void MapService_Update_ReturnsUpdatedMap() {
		
		List<MapTrack> tracks1 = new ArrayList<>();
		
		tracks1.add(MapTrack.builder().name("route 1").rbs(15).build());
		tracks1.add(MapTrack.builder().name("route 2").rbs(30).build());
		
		Map map = Map.builder()
				.id("id")
				.name("any_map1")
				.difficulty("Beginner")
				.tracks(tracks1)
				.build();
		
		List<MapTrack> tracks2 = new ArrayList<>();
		tracks2.add(MapTrack.builder().name("route 3").rbs(30).build());
		
		MapUpdateRequest mapUpdateRequest = MapUpdateRequest.builder()
				.tracks(tracks2)
				.build();
		
		Map updatedMap = Map.builder()
				.id("id")
				.name("any_map1")
				.difficulty("Beginner")
				.tracks(tracks2)
				.build();
		
		Mockito.when(mr.findById(Mockito.anyString())).thenReturn(Optional.of(map));
		
		Map response = ms.update("id", mapUpdateRequest);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(updatedMap, response);
	}
	
	@Test
	public void MapService_Update_ReturnsNull() {
		Mockito.when(mr.findById(Mockito.anyString())).thenReturn(Optional.empty());
		MapUpdateRequest mapUpdateRequest = MapUpdateRequest.builder()
				.tracks(new ArrayList<>())
				.build();
		Map response = ms.update("id", mapUpdateRequest);
		Assertions.assertNull(response);
	}
	
	@Test
	public void MapService_Save_ReturnsMap() {
		List<MapTrack> tracks1 = new ArrayList<>();
		
		tracks1.add(MapTrack.builder().name("route 1").rbs(15).build());
		tracks1.add(MapTrack.builder().name("route 2").rbs(30).build());
		
		MapSaveRequest mapSaveRequest = MapSaveRequest.builder()
				.name("any_map1")
				.difficulty("Beginner")
				.tracks(tracks1)
				.build();
		
		Mockito.when(mr.findByName(Mockito.anyString())).thenReturn(Optional.empty());
		
		Map response = ms.save(mapSaveRequest);
		Assertions.assertNull(response);
	}
	
	@Test
	public void MapService_Save_ThrowsValueNotValidException() {
		List<MapTrack> tracks1 = new ArrayList<>();
		
		tracks1.add(MapTrack.builder().name("route 1").rbs(15).build());
		tracks1.add(MapTrack.builder().name("route 2").rbs(30).build());
		
		MapSaveRequest mapSaveRequest = MapSaveRequest.builder()
				.name("any_map1")
				.difficulty("Beginner")
				.tracks(tracks1)
				.build();
		
		Map map = Map.builder()
				.name("any_map1")
				.difficulty("Beginner")
				.tracks(tracks1)
				.build();
		
		Mockito.when(mr.findByName(Mockito.anyString())).thenReturn(Optional.of(map));
		
		Assertions.assertThrows(ValueNotValidException.class, () -> ms.save(mapSaveRequest));
	}
}
