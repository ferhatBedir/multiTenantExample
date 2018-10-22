package com.ferhat.multitenant.service;

import com.ferhat.multitenant.entity.Database;
import com.ferhat.multitenant.repository.DatabaseRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private DatabaseRepository databaseRepository;

    public DatabaseService(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public void addDatabase(Database database) {
        databaseRepository.save(database);
    }

    public void deleteDatabase(String dbName) {
        Database database = databaseRepository.findFirstByDatabaseNameEqualsIgnoreCaseAndIsActive(dbName, true);
        database.setIsActive(false);
        databaseRepository.save(database);
    }
}
