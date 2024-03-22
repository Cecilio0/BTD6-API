package com.dread9182.BTD6API.service;

import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.tower.ITowerRepository;
import com.dread9182.BTD6API.tower.model.Tower;
import com.dread9182.BTD6API.tower.model.request.TowerSaveRequest;
import com.dread9182.BTD6API.tower.model.request.TowerUpdateRequest;
import com.dread9182.BTD6API.tower.service.TowerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TowerServiceTests {
	
	@InjectMocks
	private TowerService ts;
	
	@Mock
	private ITowerRepository tr;
	
	@Test
	public void TowerService_FindAll_ReturnsTowerList() {
		List<Tower> towers = new ArrayList<>();
		
		towers.add(Tower.builder().name("Tower1").build());
		towers.add(Tower.builder().name("Tower2").build());
		
		Mockito.when(tr.findAll()).thenReturn(towers);
		
		List<Tower> response = ts.findAll();
		Assertions.assertNotNull(response);
		Assertions.assertEquals(towers, response);
	}
	
	@Test
	public void TowerService_FindById_ReturnsTower() {
		
		Tower tower = Tower.builder().name("Tower1").build();
		
		Mockito.when(tr.findById(Mockito.anyString())).thenReturn(Optional.of(tower));
		
		Tower response = ts.findById("any_id");
		Assertions.assertNotNull(response);
		Assertions.assertEquals(tower, response);
	}
	
	@Test
	public void TowerService_FindById_ReturnsNull() {
		Mockito.when(tr.findById(Mockito.anyString())).thenReturn(Optional.empty());
		
		Tower response = ts.findById("any_id");
		Assertions.assertNull(response);
	}
	
	@Test
	public void TowerService_FindByName_ReturnsTower() {
		Tower tower = Tower.builder()
				.name("Tower1")
				.build();
		
		Mockito.when(tr.findByName(Mockito.anyString())).thenReturn(Optional.of(tower));
		
		Tower response = ts.findByName("any_name");
		Assertions.assertNotNull(response);
		Assertions.assertEquals(tower, response);
	}
	
	@Test
	public void TowerService_FindByName_ReturnsNull() {
		Mockito.when(tr.findByName(Mockito.anyString())).thenReturn(Optional.empty());
		
		Tower response = ts.findByName("name");
		Assertions.assertNull(response);
	}
	
	@Test
	public void TowerService_FindByType_ReturnsTower() {
		
		List<Tower> towers = new ArrayList<>();
		
		towers.add(Tower.builder()
				.name("Tower1")
				.build()
		);
		
		towers.add(Tower.builder()
				.name("Tower2")
				.build()
		);
		
		Mockito.when(tr.findByType(Mockito.anyString())).thenReturn(towers);
		
		List<Tower> response = ts.findByType("Beginner");
		Assertions.assertNotNull(response);
		Assertions.assertEquals(towers, response);
	}
	
	@Test
	public void TowerService_Update_ReturnsUpdatedTower() {
		
		Tower tower = Tower.builder()
				.id("id")
				.name("any_tower1")
				.build();
		
		TowerUpdateRequest towerUpdateRequest = TowerUpdateRequest.builder()
				.build();
		
		Tower updatedTower = Tower.builder()
				.id("id")
				.name("any_tower1")
				.build();
		
		Mockito.when(tr.findById(Mockito.anyString())).thenReturn(Optional.of(tower));
		
		Tower response = ts.update("id", towerUpdateRequest);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(updatedTower, response);
	}
	
	@Test
	public void TowerService_Update_ReturnsNull() {
		Mockito.when(tr.findById(Mockito.anyString())).thenReturn(Optional.empty());
		TowerUpdateRequest towerUpdateRequest = TowerUpdateRequest.builder()
				.build();
		Tower response = ts.update("id", towerUpdateRequest);
		Assertions.assertNull(response);
	}
	
	@Test
	public void TowerService_Save_ReturnsTower() {
		TowerSaveRequest towerSaveRequest = TowerSaveRequest.builder()
				.name("any_tower1")
				.build();
		
		Mockito.when(tr.findByName(Mockito.anyString())).thenReturn(Optional.empty());
		
		Tower response = ts.save(towerSaveRequest);
		Assertions.assertNull(response);
	}
	
	@Test
	public void TowerService_Save_ThrowsValueNotValidException() {
		TowerSaveRequest towerSaveRequest = TowerSaveRequest.builder()
				.name("Tower1")
				.build();
		
		Tower tower = Tower.builder()
				.name("Tower1")
				.build();
		
		Mockito.when(tr.findByName(Mockito.anyString())).thenReturn(Optional.of(tower));
		
		Assertions.assertThrows(ValueNotValidException.class, () -> ts.save(towerSaveRequest));
	}
	
}
