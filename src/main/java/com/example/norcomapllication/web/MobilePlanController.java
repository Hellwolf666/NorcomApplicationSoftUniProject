package com.example.norcomapllication.web;

import com.example.norcomapllication.model.binding.MobilePlanAddBindingModel;
import com.example.norcomapllication.model.binding.MobilePlanUpdateBindingModel;
import com.example.norcomapllication.model.service.MobilePlanAddServiceModel;
import com.example.norcomapllication.model.service.MobilePlanServiceUpdate;
import com.example.norcomapllication.model.view.MobilePlanDetailsView;
import com.example.norcomapllication.service.MobilePlanService;
import com.example.norcomapllication.service.impl.NorcomUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MobilePlanController {
    private final MobilePlanService mobilePlanService;
    private final ModelMapper modelMapper;


    public MobilePlanController(MobilePlanService mobilePlanService, ModelMapper modelMapper) {
        this.mobilePlanService = mobilePlanService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/mobile-plans/all")
    public String allMobilePlans(Model model) {
        String fourG = "4G";
        String fiveG = "5G";
        model.addAttribute("plans4G",mobilePlanService.getAll4GTypePlans(fourG));
        model.addAttribute("plans5G",mobilePlanService.getAll5GTypePlans(fiveG));
        return "mobile-plans";
    }
    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("/mobile-plans/{id}")
    public String deleteMobilePlan(@PathVariable Long id, Principal principal) {
        mobilePlanService.deleteMobilePlan(id);
        return "redirect:/mobile-plans/all";
    }
    @GetMapping("/mobile-plans/add")
    public String addMobilePlanPage(Model model) {
        if(!model.containsAttribute("mobilePlanAddBindingModel")) {
            model.addAttribute("mobilePlanAddBindingModel", new MobilePlanAddBindingModel());
        }
        return "add-mobile-plan";
    }
    @PostMapping("/mobile-plans/add")
    public String addMobilePlan(@Valid MobilePlanAddBindingModel mobilePlanAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, NorcomUser user) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mobilePlanAddBindingModel",mobilePlanAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mobilePlanAddBindingModel",bindingResult);
            return "redirect:mobile-plan/add";
        }
        mobilePlanService.addMobilePlan(mobilePlanAddBindingModel,user.getUserIdentifier());
        return "redirect:/mobile-plans/all";
    }
    @GetMapping("/mobile-plans/{id}/edit-mobile-plan")
    public String editMobilePlan(@PathVariable Long id, Model model, @AuthenticationPrincipal NorcomUser user) {
        MobilePlanDetailsView mobilePlanDetailsView = mobilePlanService.findById(id,user.getUserIdentifier());
        MobilePlanUpdateBindingModel mobilePlanUpdateBindingModel = modelMapper.map(mobilePlanDetailsView, MobilePlanUpdateBindingModel.class);
        model.addAttribute("mobilePlanUpdateBindingModel",mobilePlanUpdateBindingModel);
        return "edit-mobile-plan";
    }

//    @GetMapping("/mobile-plans/{id}/edit-mobile-plan/errors")
//    public String editOfferErrors(@PathVariable Long id, Model model) {
//        return "edit-mobile-plan";
//    }

    @PatchMapping("/mobile-plans/{id}/edit-mobile-plan")
    public String editMobilePlan(@PathVariable Long id,@Valid MobilePlanUpdateBindingModel mobilePlanUpdateBindingModel,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mobilePlanUpdateBindingModel",mobilePlanUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mobilePlanUpdateBindingModel",mobilePlanUpdateBindingModel);
            return "redirect:/mobile-plans" + id +"/edit-mobile-plan";
        }
        MobilePlanServiceUpdate mobilePlanServiceUpdate = modelMapper.map(mobilePlanUpdateBindingModel,MobilePlanServiceUpdate.class);
        mobilePlanServiceUpdate.setId(id);
        mobilePlanService.updateMobilePlan(mobilePlanServiceUpdate);
        return "redirect:/mobile-plans/all";
    }
}
