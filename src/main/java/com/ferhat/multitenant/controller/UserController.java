package com.ferhat.multitenant.controller;

import com.ferhat.multitenant.exceptions.ExceptionMessage;
import com.ferhat.multitenant.exceptions.InvalidParametersException;
import com.ferhat.multitenant.model.UserModel;
import com.ferhat.multitenant.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "User Controller")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public void addUserToDb(@RequestBody UserModel userModel, BindingResult bindingResult) throws InvocationTargetException, IllegalAccessException {
        if (bindingResult.hasErrors()) {
            throw new InvalidParametersException(ExceptionMessage.INVALID_PARAMETERS);
        }
        userService.addUser(userModel);
    }

    @GetMapping("/findall")
    public List<UserModel> findAllUsers(@RequestParam(value = "dbname") String databaseName) {
        return userService.findAll(databaseName);
    }

    @GetMapping("/findlastname")
    public List<UserModel> findUserSameLastName(@RequestParam(value = "lastname") String lastName,
                                                @RequestParam(value = "dbname") String databaseName) {
        return userService.findUser(lastName, databaseName);
    }

}
