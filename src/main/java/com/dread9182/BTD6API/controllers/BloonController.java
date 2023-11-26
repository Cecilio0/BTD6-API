package com.dread9182.BTD6API.controllers;

import com.dread9182.BTD6API.exception.NotFoundException;
import com.dread9182.BTD6API.models.Bloon;
import com.dread9182.BTD6API.services.bloon.IBloonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bloons")
public class BloonController {
	@Autowired
	IBloonService bs;
	
	@GetMapping("/find")
	public ResponseEntity<List<Bloon>> findAll(){
		return new ResponseEntity<>(bs.findAll(), HttpStatus.OK);
	}
	
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
	
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Bloon>> findByType(@PathVariable String type){
		return new ResponseEntity<>(bs.findByType(type), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Bloon> update(@PathVariable String id, @RequestBody Bloon bloon){
		Bloon responseObject = bs.update(id, bloon);
		
		if(responseObject == null)
			throw new NotFoundException("No bloon with the specified id was found");
		
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}
	
}
