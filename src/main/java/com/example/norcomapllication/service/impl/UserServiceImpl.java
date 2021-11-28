package com.example.norcomapllication.service.impl;

import com.example.norcomapllication.model.entity.Role;
import com.example.norcomapllication.model.entity.User;
import com.example.norcomapllication.model.entity.enums.RoleEnumClass;
import com.example.norcomapllication.model.service.UserRegisterServiceModel;
import com.example.norcomapllication.repository.RoleRepository;
import com.example.norcomapllication.repository.UserRepository;
import com.example.norcomapllication.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final NorcomUserServiceImpl norcomUserService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, NorcomUserServiceImpl norcomUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.norcomUserService = norcomUserService;
    }

    @Override
    public void initUsers() {
        initializeUsers();
    }

    @Override
    public boolean isFreeUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();

    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            Role adminRole = roleRepository.findByRole(RoleEnumClass.ADMIN);
            Role userRole = roleRepository.findByRole(RoleEnumClass.USER);

            User admin = new User();
            admin.setFullName("Admin Adminov");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("test"));
            admin.setAddress("st.Rezbarska 69");
            admin.setEmail("adminovk@gmail.com");
            admin.setGender("male");
            admin.setRegisterDate("2021/27/11");
            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            User user = new User();
            user.setFullName("Troyan Userov");
            user.setUsername("troyan");
            user.setPassword(passwordEncoder.encode("troy4o"));
            user.setAddress("st.Stamboliiski 47");
            user.setEmail("userovt@gmail.com");
            user.setGender("male");
            user.setRegisterDate("2021/27/11");
            user.setRoles(Set.of(adminRole, userRole));
            userRepository.save(user);

        }
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {
        Role role = roleRepository.findByRole(RoleEnumClass.USER);
        User newUser = new User();
        newUser.setFullName(userRegisterServiceModel.getFullName());
        newUser.setUsername(userRegisterServiceModel.getUsername());
        newUser.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));
        newUser.setAddress(userRegisterServiceModel.getAddress());
        newUser.setEmail(userRegisterServiceModel.getEmail());
        newUser.setGender(userRegisterServiceModel.getGender());
        newUser.setRegisterDate(userRegisterServiceModel.getRegisterDate());
        newUser.setRoles(Set.of(role));
        newUser = userRepository.save(newUser);

        UserDetails principal = norcomUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
