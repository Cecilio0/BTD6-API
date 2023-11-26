package com.dread9182.BTD6API.controllers;

import com.dread9182.BTD6API.exception.NotFoundException;
import com.dread9182.BTD6API.models.Bloon;
import com.dread9182.BTD6API.models.Map;
import com.dread9182.BTD6API.services.map.IMapService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapController {
	@Autowired
	IMapService ms;
	
	@GetMapping("/find")
	public ResponseEntity<List<Map>> findAll(){
		return new ResponseEntity<>(ms.findAll(), HttpStatus.OK);
	}
	
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
	
	@GetMapping("/difficulty/{difficulty}")
	public ResponseEntity<List<Map>> findByIdOrName(@PathVariable String difficulty){
		return new ResponseEntity<>(ms.findByDifficulty(difficulty), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Map> findByIdOrName(@PathVariable String id, @RequestBody Map map){
		Map responseObject = ms.update(id, map);
		
		if (responseObject == null)
			throw new NotFoundException("No map with the specified id was found");
		
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}
}
