package com.dread9182.BTD6API.repositories;

import com.dread9182.BTD6API.models.Tower;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ITowerRepository extends MongoRepository<Tower, String> {
	
	Optional<Tower> findByName(String name);
	
	List<Tower> findByType(String type);
}
