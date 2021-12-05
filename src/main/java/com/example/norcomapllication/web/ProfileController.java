package com.example.norcomapllication.web;

import com.example.norcomapllication.model.binding.ProfileUpdateBindingModel;
import com.example.norcomapllication.model.entity.User;
import com.example.norcomapllication.model.service.MobilePlanServiceUpdate;
import com.example.norcomapllication.model.service.ProfileUpdateServiceModel;
import com.example.norcomapllication.model.view.ProfileUserView;
import com.example.norcomapllication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;
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

        addProfileView(model);

        return "profile";
    }



    @GetMapping("/profile/{id}/edit-profile")
    public String editProfile(Model model) {
    getUserProfile(model);
    return "edit-profile";
    }

    @GetMapping("/profile/{id}/edit-profile/errors")
    public String editProfileErrors(@PathVariable Long id, Model model) {

        return "edit-mobile-plan";
    }

    @PatchMapping("/profile/{id}/edit-profile")
    public String editMobilePlan(@PathVariable Long id, @Valid ProfileUpdateBindingModel profileUpdateBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes,@RequestParam(value = "gender", required = false) String gender) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("profileUpdateBindingModel",profileUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.profileUpdateBindingModel",bindingResult);
            return "redirect:/profile/" + id +"/edit-profile/errors";
        }

        ProfileUpdateServiceModel profileUpdateServiceModel = modelMapper.map(profileUpdateBindingModel,ProfileUpdateServiceModel.class);
        profileUpdateBindingModel.setId(id);
        profileUpdateServiceModel.setGender(gender);
        userService.updateProfile(profileUpdateServiceModel);
        return "redirect:/users/profile";
    }

    private Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.findByUsername(username);
    }

    private void addProfileView(Model model) {
        Optional<User> userEntity = getCurrentUser();
        ProfileUserView user = new ProfileUserView();
        user.setId(userEntity.get().getId());
        user.setFullName(userEntity.get().getFullName());
        user.setAddress(userEntity.get().getAddress());
        user.setGender(userEntity.get().getGender());
        user.setUsername(userEntity.get().getUsername());
        user.setEmail(userEntity.get().getEmail());
        model.addAttribute("user", user);
    }
}
