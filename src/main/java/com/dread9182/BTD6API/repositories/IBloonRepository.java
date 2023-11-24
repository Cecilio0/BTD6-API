package com.dread9182.BTD6API.repositories;

import com.dread9182.BTD6API.models.Bloon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IBloonRepository extends MongoRepository<Bloon, String> {
	Bloon findByName(String name);
	
	List<Bloon> findByType(String type);
}
