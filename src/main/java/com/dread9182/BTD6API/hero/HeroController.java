package com.dread9182.BTD6API.hero;

import com.dread9182.BTD6API.exception.NotFoundException;
import com.dread9182.BTD6API.hero.model.Hero;
import com.dread9182.BTD6API.hero.service.IHeroService;
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
@RequestMapping("/heroes")
@SecurityRequirement(name = "bearerAuth")
@Tag(
		name = "Hero controller",
		description = "Controller in charge of all requests related to BTD6 hero information"
)
public class HeroController {
	@Autowired
	private final IHeroService hs;
	@Operation(
			summary = "Find all heroes"
	)
	@GetMapping("/find")
	public ResponseEntity<List<Hero>> findAll(){
		return new ResponseEntity<>(hs.findAll(), HttpStatus.OK);
	}
	
	@Operation(
			summary = "Find a hero by id or name"
	)
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
	@Operation(
			summary = "Find heroes by unlock method"
	)
	@GetMapping("/how/{how}")
	public ResponseEntity<List<Hero>> findByHowIsUnlocked(@PathVariable String how) {
		return new ResponseEntity<>(hs.findByHowIsUnlocked(how), HttpStatus.OK);
	}
	
	@Operation(
			summary = "Update an existing hero"
	)
	@PutMapping("/update/{id}")
	public ResponseEntity<Hero> update(@PathVariable String id, @RequestBody Hero hero){
		return new ResponseEntity<>(hs.update(id, hero), HttpStatus.OK);
	}
	
	// todo create endpoint for hero creation
}
