package com.ferhat.multitenant.service;


import com.ferhat.multitenant.TenantContext;
import com.ferhat.multitenant.entity.User;
import com.ferhat.multitenant.exceptions.ExceptionMessage;
import com.ferhat.multitenant.exceptions.InvalidParametersException;
import com.ferhat.multitenant.model.UserAddModel;
import com.ferhat.multitenant.model.UserModel;
import com.ferhat.multitenant.repository.UserRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserAddModel userAddModel) throws InvocationTargetException, IllegalAccessException {
        TenantContext.setTenant(userAddModel.getDatabaseName());
        User user = toUser(userAddModel);
        if (user == null) {
            throw new InvalidParametersException(ExceptionMessage.INVALID_PARAMETERS);
        }
        userRepository.save(user);
    }

    public List<UserModel> findAll(String databaseName) {
        TenantContext.setTenant(databaseName);
        List<User> users = userRepository.findAll();
        return toUserModel(users);
    }

    public List<UserModel> findUser(String lastName, String databaseName) {
        TenantContext.setTenant(databaseName);
        List<User> users = userRepository.findByUserLastNameEqualsIgnoreCase(lastName);
        return toUserModel(users);
    }

    private User toUser(UserAddModel userAddModel) throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        BeanUtils.copyProperties(user, userAddModel);
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
