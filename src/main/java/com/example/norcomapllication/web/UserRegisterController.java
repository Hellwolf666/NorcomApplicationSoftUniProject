package com.example.norcomapllication.web;

import com.example.norcomapllication.model.binding.UserRegisterBindingModel;
import com.example.norcomapllication.model.service.UserRegisterServiceModel;
import com.example.norcomapllication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserRegisterController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @ModelAttribute("userModel")
    public UserRegisterBindingModel userModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String register() {
        return "/register";
    }
    @PostMapping("register")
    public String registerConfirm(@Valid UserRegisterBindingModel usermodel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || !usermodel.getPassword().equals(usermodel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userModel",usermodel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",bindingResult);
            return "redirect:/users/register";
        }
        UserRegisterServiceModel serviceModel = modelMapper.map(usermodel,UserRegisterServiceModel.class);
        userService.registerAndLoginUser(serviceModel);
        return "";
    }
}
//TODO register.html не намира полетата