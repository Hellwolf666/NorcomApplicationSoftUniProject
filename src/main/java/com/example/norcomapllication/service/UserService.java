package com.example.norcomapllication.service;

import com.example.norcomapllication.model.service.UserRegisterServiceModel;

public interface UserService {

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    void initUsers();

    boolean isFreeUsername(String username);
}
