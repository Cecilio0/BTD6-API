package com.dread9182.BTD6API.repositories;

import com.dread9182.BTD6API.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<String, User> {
}
