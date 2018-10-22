package com.ferhat.multitenant.service;


import com.ferhat.multitenant.config.MultiTenancyService;
import com.ferhat.multitenant.entity.Database;
import com.ferhat.multitenant.entity.User;
import com.ferhat.multitenant.exceptions.ExceptionMessage;
import com.ferhat.multitenant.exceptions.InvalidParametersException;
import com.ferhat.multitenant.model.UserModel;
import com.ferhat.multitenant.repository.DatabaseRepository;
import com.ferhat.multitenant.repository.UserRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private MultiTenancyService multiTenancyService;
    private DatabaseRepository databaseRepository;

    public UserService(UserRepository userRepository, MultiTenancyService multiTenancyService, DatabaseRepository databaseRepository) {
        this.userRepository = userRepository;
        this.multiTenancyService = multiTenancyService;
        this.databaseRepository = databaseRepository;
    }

    public void addUser(UserModel userModel) throws InvocationTargetException, IllegalAccessException {
        multiTenancyService.setTenant(userModel.getDatabaseName());
        User user = toUser(userModel);
        if (user == null) {
            throw new InvalidParametersException(ExceptionMessage.INVALID_PARAMETERS);
        }
        userRepository.save(user);
    }

    public List<UserModel> findAll(String databaseName) {
        multiTenancyService.setTenant(databaseName);
        List<User> users = userRepository.findAll();
        return toUserModel(users);
    }

    public List<UserModel> findUser(String lastName, String databaseName) {
        multiTenancyService.setTenant(databaseName);
        List<User> users = userRepository.findByUserLastNameEqualsIgnoreCase(lastName);
        return toUserModel(users);
    }

    private User toUser(UserModel userModel) throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        BeanUtils.copyProperties(user, userModel);
        return user;
    }

    private List<UserModel> toUserModel(List<User> users) {
        List<UserModel> userModels = new ArrayList<>();
        users.forEach(user -> {
            UserModel userModel = new UserModel();
            try {
                BeanUtils.copyProperties(userModel, user);
                userModels.add(userModel);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return userModels;
    }


}
