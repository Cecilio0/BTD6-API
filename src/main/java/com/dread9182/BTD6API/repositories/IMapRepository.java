package com.dread9182.BTD6API.repositories;

import com.dread9182.BTD6API.models.Map;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IMapRepository extends MongoRepository<Map, String> {
	Optional<Map> findByName(String name);
	
	List<Map> findByDifficulty(String difficulty);
}
