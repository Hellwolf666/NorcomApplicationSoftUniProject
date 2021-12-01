package com.example.norcomapllication.web;

import com.example.norcomapllication.model.binding.ProfilleUpdateBindingModel;
import com.example.norcomapllication.model.entity.User;
import com.example.norcomapllication.model.service.ProfileUpdateServiceModel;
import com.example.norcomapllication.model.view.ProfileUserView;
import com.example.norcomapllication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class ProfileController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ProfileController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model){

        Optional<User> userEntity = getCurrentUser();
        ProfileUserView user = new ProfileUserView();
        user.setId(userEntity.get().getId());
        user.setFullName(userEntity.get().getFullName());
        user.setAddress(userEntity.get().getAddress());
        user.setGender(userEntity.get().getGender());
        user.setEmail(userEntity.get().getEmail());
        model.addAttribute("user", user);

        return "profile";
    }

    private Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.findByUsername(username);
    }


}
