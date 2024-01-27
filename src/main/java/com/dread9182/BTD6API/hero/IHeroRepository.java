package com.dread9182.BTD6API.hero;

import com.dread9182.BTD6API.hero.model.Hero;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IHeroRepository extends MongoRepository<Hero, String> {
	Optional<Hero> findByName(String name);
}
