package com.dread9182.BTD6API.bloon;

import com.dread9182.BTD6API.bloon.model.Bloon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IBloonRepository extends MongoRepository<Bloon, String> {
	Optional<Bloon> findByName(String name);
	
	List<Bloon> findByType(String type);
}
