package com.dread9182.BTD6API.user;

import com.dread9182.BTD6API.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User, String> {
	
	Optional<User> findByEmail(String email);
	
}
