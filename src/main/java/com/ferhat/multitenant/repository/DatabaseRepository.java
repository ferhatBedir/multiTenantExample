package com.ferhat.multitenant.repository;

import com.ferhat.multitenant.entity.Database;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseRepository extends MongoRepository<Database, String> {

    Database findFirstByDatabaseNameEqualsIgnoreCaseAndIsActive(String databaseName, boolean isActive);
}
