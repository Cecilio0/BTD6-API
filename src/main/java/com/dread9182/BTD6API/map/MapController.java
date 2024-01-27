package com.dread9182.BTD6API.map;

import com.dread9182.BTD6API.exception.NotFoundException;
import com.dread9182.BTD6API.map.model.Map;
import com.dread9182.BTD6API.map.service.IMapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/maps")
@SecurityRequirement(name = "bearerAuth")
@Tag(
		name = "Map controller",
		description = "Controller in charge of all requests related to BTD6 map information"
)
public class MapController {
	@Autowired
	private final IMapService ms;
	
	@Operation(
			summary = "Find all maps"
	)
	@GetMapping("/find")
	public ResponseEntity<List<Map>> findAll(){
		return new ResponseEntity<>(ms.findAll(), HttpStatus.OK);
	}
	
	@Operation(
			summary = "Find map by id or name"
	)
	@GetMapping("/find/{id}")
	public ResponseEntity<Map> findByIdOrName(@PathVariable String id, @RequestParam(required = false, name = "name", defaultValue = "false") boolean name){
		Map responseObject = name? ms.findByName(id) : ms.findById(id);
		
		if (responseObject == null){
			if (name){
				throw new NotFoundException("No map with the specified name was found");
			} else {
				throw new NotFoundException("No map with the specified id was found");
			}
		}
		
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Find maps by difficulty"
	)
	@GetMapping("/difficulty/{difficulty}")
	public ResponseEntity<List<Map>> findByIdOrName(@PathVariable String difficulty){
		return new ResponseEntity<>(ms.findByDifficulty(difficulty), HttpStatus.OK);
	}
	
	@Operation(
			summary = "Update an existing map"
	)
	@PutMapping("/update/{id}")
	public ResponseEntity<Map> findByIdOrName(@PathVariable String id, @RequestBody Map map){
		Map responseObject = ms.update(id, map);
		
		if (responseObject == null)
			throw new NotFoundException("No map with the specified id was found");
		
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}
	
	// todo create endpoint for map creation
}
