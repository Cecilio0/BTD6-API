package com.dread9182.BTD6API.repositories;

import com.dread9182.BTD6API.models.Hero;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IHeroRepository extends MongoRepository<Hero, String> {
	Optional<Hero> findByName(String name);
}
