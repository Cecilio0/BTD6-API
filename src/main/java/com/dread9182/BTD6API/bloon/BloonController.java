package com.dread9182.BTD6API.bloon;

import com.dread9182.BTD6API.bloon.model.Bloon;
import com.dread9182.BTD6API.bloon.service.IBloonService;
import com.dread9182.BTD6API.exception.NotFoundException;
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
@RequestMapping("/bloons")
@SecurityRequirement(name = "bearerAuth")@Tag(
		name = "Bloon controller",
		description = "Controller in charge of all requests related to BTD6 bloon information"
)

public class BloonController {
	@Autowired
	private final IBloonService bs;
	@Operation(
			summary = "Find all bloons"
	)
	@GetMapping("/find")
	public ResponseEntity<List<Bloon>> findAll(){
		return new ResponseEntity<>(bs.findAll(), HttpStatus.OK);
	}
	
	@Operation(
			summary = "Find a bloon by id or name"
	)
	@GetMapping("/find/{id}")
	public ResponseEntity<Bloon> findByIdOrName(@PathVariable String id, @RequestParam(required = false, name = "name", defaultValue = "false") boolean name){
		Bloon responseObject = name? bs.findByName(id) : bs.findById(id);
		
		if (responseObject == null){
			if (name){
				throw new NotFoundException("No bloon with the specified name was found");
			} else {
				throw new NotFoundException("No bloon with the specified id was found");
			}
		}
		
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Find bloons by type"
	)
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Bloon>> findByType(@PathVariable String type){
		return new ResponseEntity<>(bs.findByType(type), HttpStatus.OK);
	}
	// todo Create and implement DTO for update and save, as well as implementing interceptors for said DTO
	@Operation(
			summary = "Update an existing bloon"
	)
	@PutMapping("/update/{id}")
	public ResponseEntity<Bloon> update(@PathVariable String id, @RequestBody Bloon bloon){
		Bloon responseObject = bs.update(id, bloon);
		
		if(responseObject == null)
			throw new NotFoundException("No bloon with the specified id was found");
		
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Create a new bloon"
	)
	@PostMapping("/save")
	public ResponseEntity<Bloon> save(@RequestBody Bloon bloon){
		Bloon responseObject = bs.save(bloon);
		
		return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
	}
}
