package com.dread9182.BTD6API.tower;

import com.dread9182.BTD6API.exception.NotFoundException;
import com.dread9182.BTD6API.tower.model.Tower;
import com.dread9182.BTD6API.tower.service.ITowerService;
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
@RequestMapping("/towers")
@SecurityRequirement(name = "bearerAuth")
@Tag(
		name = "Tower controller",
		description = "Controller in charge of all requests related to BTD6 tower information"
)
public class TowerController {
	
	@Autowired
	private final ITowerService ts;
	
	@Operation(
			summary = "Find all towers"
	)
	@GetMapping("/find")
	public ResponseEntity<List<Tower>> findAll(){
		return new ResponseEntity<>(ts.findAll(), HttpStatus.OK);
	}
	
	@Operation(
			summary = "Find tower by id or name"
	)
	@GetMapping("/find/{id}")
	public ResponseEntity<Tower> findByIdOrName(@PathVariable String id, @RequestParam(required = false, name = "name", defaultValue = "false") boolean name){
		Tower responseObject = name? ts.findByName(id) : ts.findById(id);
		
		if (responseObject == null){
			if (name){
				throw new NotFoundException("No tower with the specified name was found");
			} else {
				throw new NotFoundException("No tower with the specified id was found");
			}
		}
		
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Find towers by type"
	)
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Tower>> findByType(@PathVariable String type){
		return new ResponseEntity<>(ts.findByType(type), HttpStatus.OK);
	}
	
	@Operation(
			summary = "Update an existing tower"
	)
	@PutMapping("/update/{id}")
	public ResponseEntity<Tower> update(@PathVariable String id, @RequestBody Tower tower){
		Tower result = ts.update(id, tower);
		if(result == null)
			throw new NotFoundException("No tower found with the given id");
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// todo create endpoint for tower creation
}
