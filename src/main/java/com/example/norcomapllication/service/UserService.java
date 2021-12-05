package com.example.norcomapllication.service;

import com.example.norcomapllication.model.entity.User;
import com.example.norcomapllication.model.service.ProfileUpdateServiceModel;
import com.example.norcomapllication.model.service.UserRegisterServiceModel;
import com.example.norcomapllication.model.view.AdminUsersViewModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    void initUsersAndRoles();

    boolean isFreeUsername(String username);

    Optional<User> findByUsername(String username);

    void updateProfile(ProfileUpdateServiceModel profileUpdateServiceModel);

    List<AdminUsersViewModel> getAllUsersByFetch();

    void deleteUserById(Long id);

    void promoteToAdmin(Long id);

    void demoteAdmin(Long id);
}
