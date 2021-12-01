package com.example.norcomapllication.service;

import com.example.norcomapllication.model.entity.User;
import com.example.norcomapllication.model.service.UserRegisterServiceModel;

import java.util.Optional;

public interface UserService {

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    void initUsersAndRoles();

    boolean isFreeUsername(String username);

    Optional<User> findByUsername(String username);

}
