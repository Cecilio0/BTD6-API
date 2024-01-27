package com.dread9182.BTD6API.hero;

import com.dread9182.BTD6API.exception.NotFoundException;
import com.dread9182.BTD6API.hero.model.Hero;
import com.dread9182.BTD6API.hero.service.IHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {
	@Autowired
	IHeroService hs;
	
	@GetMapping("/find")
	public ResponseEntity<List<Hero>> findAll(){
		return new ResponseEntity<>(hs.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Hero> findByIdOrName(@PathVariable String id, @RequestParam(required = false, name = "name", defaultValue = "false") boolean name){
		Hero responseObject = name? hs.findByName(id): hs.findById(id);
		
		if (responseObject == null){
			if (name){
				throw new NotFoundException("No hero with the specified name was found");
			} else {
				throw new NotFoundException("No hero with the specified id was found");
			}
		}
		
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}
	
	@GetMapping("/how/{how}")
	public ResponseEntity<List<Hero>> findByHowIsUnlocked(@PathVariable String how) {
		return new ResponseEntity<>(hs.findByHowIsUnlocked(how), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Hero> update(@PathVariable String id, @RequestBody Hero hero){
		return new ResponseEntity<>(hs.update(id, hero), HttpStatus.OK);
	}
	
}
