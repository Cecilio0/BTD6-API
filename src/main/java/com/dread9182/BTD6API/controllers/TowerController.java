package com.dread9182.BTD6API.controllers;

import com.dread9182.BTD6API.exception.NotFoundException;
import com.dread9182.BTD6API.models.Tower;
import com.dread9182.BTD6API.services.tower.ITowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/towers")
public class TowerController {
	
	@Autowired
	private ITowerService ts;
	
	@GetMapping("/find")
	public ResponseEntity<List<Tower>> findAll(){
		return new ResponseEntity<>(ts.findAll(), HttpStatus.OK);
	}
	
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
	
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Tower>> findByType(@PathVariable String type){
		return new ResponseEntity<>(ts.findByType(type), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Tower> update(@PathVariable String id, @RequestBody Tower tower){
		return new ResponseEntity<>(ts.update(id, tower), HttpStatus.OK);
	}
}
