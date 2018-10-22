package com.ferhat.multitenant.controller;

import com.ferhat.multitenant.entity.Database;
import com.ferhat.multitenant.exceptions.ExceptionMessage;
import com.ferhat.multitenant.exceptions.InvalidParametersException;
import com.ferhat.multitenant.service.DatabaseService;
import io.swagger.annotations.Api;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/database")
@Api(tags = "Database Controller")
public class DatabaseController {

    private DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @PostMapping("/add")
    public void addDatabase(@RequestBody Database database, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParametersException(ExceptionMessage.INVALID_PARAMETERS);
        }
        databaseService.addDatabase(database);
    }

    @GetMapping("/delete")
    public void deleteDatabase(@RequestParam(value = "dbname")String dbName) {
        databaseService.deleteDatabase(dbName);
    }

}
