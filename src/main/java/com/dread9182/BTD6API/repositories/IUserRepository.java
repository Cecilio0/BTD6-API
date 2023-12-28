package com.dread9182.BTD6API.repositories;

import com.dread9182.BTD6API.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User, String> {
	
	Optional<User> findByEmail(String email);
	
}
