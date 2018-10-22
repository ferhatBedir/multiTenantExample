package com.ferhat.multitenant.repository;

import com.ferhat.multitenant.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByUserLastNameEqualsIgnoreCase(String lastName);
}
