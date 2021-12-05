package com.example.norcomapllication.service.impl;

import com.example.norcomapllication.model.entity.Role;
import com.example.norcomapllication.model.entity.User;
import com.example.norcomapllication.model.entity.enums.RoleEnumClass;
import com.example.norcomapllication.model.service.ProfileUpdateServiceModel;
import com.example.norcomapllication.model.service.UserRegisterServiceModel;
import com.example.norcomapllication.model.view.AdminUsersViewModel;
import com.example.norcomapllication.repository.RoleRepository;
import com.example.norcomapllication.repository.UserRepository;
import com.example.norcomapllication.service.UserService;
import com.example.norcomapllication.service.errorPackage.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final NorcomUserServiceImpl norcomUserService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, NorcomUserServiceImpl norcomUserService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.norcomUserService = norcomUserService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    @Override
    public boolean isFreeUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();

    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

//    @Override
//    public Optional<User> findfById(Long id) {
//        Optional<User> byId = userRepository.findById(id);
//        return byId;
//    }

    @Override
    public void updateProfile(ProfileUpdateServiceModel profileUpdateServiceModel) {
        User user = userRepository.findById(profileUpdateServiceModel.getId()).orElseThrow(() -> new ObjectNotFoundException("User with id" + profileUpdateServiceModel.getId() + "not found!"));
        user.setUsername(profileUpdateServiceModel.getUsername())
                .setAddress(profileUpdateServiceModel.getAddress())
                .setEmail(profileUpdateServiceModel.getEmail())
                .setUsername(profileUpdateServiceModel.getUsername())
                .setGender(profileUpdateServiceModel.getGender());
        userRepository.save(user);
    }

    @Override
    public List<AdminUsersViewModel> getAllUsersByFetch() {
        return userRepository.getAllUsersByFetch().stream().map(user -> modelMapper.map(user,AdminUsersViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void promoteToAdmin(Long id) {
        userRepository.findById(id)
                .ifPresent(user -> {
                    user.getRoles().remove(roleRepository.findByRole(RoleEnumClass.USER));
                    user.getRoles().add(roleRepository.findByRole(RoleEnumClass.ADMIN));
                    userRepository.save(user);
                });

    }

    @Override
    public void demoteAdmin(Long id) {
        User user= userRepository.getById(id);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRole(RoleEnumClass.USER));
        roles.remove(roleRepository.findByRole(RoleEnumClass.ADMIN));
        user.setRoles(roles);
        userRepository.save(user);
    }


    private void initializeRoles() {
        if (roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setRole(RoleEnumClass.ADMIN);

            Role user = new Role();
            user.setRole(RoleEnumClass.USER);

            roleRepository.saveAll(Set.of(admin, user));
        }
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            Role adminRole = roleRepository.findByRole(RoleEnumClass.ADMIN);
            Role userRole = roleRepository.findByRole(RoleEnumClass.USER);

            User admin = new User();
            admin.setFullName("Admin Adminov");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin4o"));
            admin.setAddress("st.Rezbarska 69");
            admin.setEmail("adminovk@gmail.com");
            admin.setGender("male");
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);

            User user = new User();
            user.setFullName("Troyan Userov");
            user.setUsername("troyan");
            user.setPassword(passwordEncoder.encode("troy4o"));
            user.setAddress("st.Stamboliiski 47");
            user.setEmail("userovt@gmail.com");
            user.setGender("male");
            user.setRoles(Set.of(userRole));
            userRepository.save(user);

        }
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {
        Role role = roleRepository.findByRole(RoleEnumClass.USER);
        User newUser = new User();
        newUser.setFullName(userRegisterServiceModel.getFullName())
                .setAddress(userRegisterServiceModel.getAddress())
                .setEmail(userRegisterServiceModel.getEmail())
                .setGender(userRegisterServiceModel.getGender())
                .setUsername(userRegisterServiceModel.getUsername())
                .setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                .setRoles(Set.of(role));
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
