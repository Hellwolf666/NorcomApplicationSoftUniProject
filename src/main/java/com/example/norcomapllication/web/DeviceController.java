package com.example.norcomapllication.web;

import com.example.norcomapllication.model.binding.DeviceAddBindingModel;
import com.example.norcomapllication.model.binding.DeviceUpdateBindingModel;
import com.example.norcomapllication.model.entity.enums.OperationSystemType;
import com.example.norcomapllication.model.service.DeviceAddServiceModel;
import com.example.norcomapllication.model.service.DeviceUpdateServiceModel;
import com.example.norcomapllication.model.view.DeviceDetailsView;
import com.example.norcomapllication.service.DeviceService;
import com.example.norcomapllication.service.impl.NorcomUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
@Controller
public class DeviceController {
    private final ModelMapper modelMapper;
    private final DeviceService deviceService;

    public DeviceController(ModelMapper modelMapper, DeviceService deviceService) {
        this.modelMapper = modelMapper;
        this.deviceService = deviceService;
    }
    @GetMapping("/devices/all")
    public String allDevices(Model model) {
        model.addAttribute("devices", deviceService.getAllDevices());
        return "devices";
    }

    @GetMapping("/devices/{id}/device-page")
    public String showDevice(
            @PathVariable Long id, Model model,
            Principal principal) {
        model.addAttribute("device", this.deviceService.findById(id, principal.getName()));
        return "device-page";
    }
    @PreAuthorize("isOwner(#id)")
//    @PreAuthorize("@deviceServiceImpl.isOwner(#principal.name, #id)")
    @DeleteMapping("/devices/{id}")
    public String deleteDevice(@PathVariable Long id,Principal principal) {
        if(!deviceService.isOwner(principal.getName(),id)) {
            throw new RuntimeException();
        }
        deviceService.deleteDevice(id);
        return "redirect:/devices/all";
    }

    @GetMapping("/devices/{id}/edit-device")
    public String editDevice(@PathVariable Long id, Model model, @AuthenticationPrincipal NorcomUser currentUser) {
        DeviceDetailsView deviceDetailsView = deviceService.findById(id,currentUser.getUserIdentifier());
        DeviceUpdateBindingModel deviceUpdateBindingModel = modelMapper.map(deviceDetailsView,DeviceUpdateBindingModel.class);
        model.addAttribute("deviceUpdateBindingModel",deviceUpdateBindingModel);
        return "edit-device";
    }

    @GetMapping("/devices/{id}/edit-device/errors")
    public String editDeviceError(@PathVariable Long id, Model model) {
        model.addAttribute("operationSystem", OperationSystemType.values());
        return "edit-device";
    }

    @PatchMapping("/devices/{id}/edit-device")
    public String editDevice(
            @PathVariable Long id,
            @Valid DeviceUpdateBindingModel deviceUpdateBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("deviceUpdateBindingModel", deviceUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.deviceUpdateBindingModel", bindingResult);

            return "redirect:/devices/" + id + "/edit-device/errors";
        }

        DeviceUpdateServiceModel serviceModel = modelMapper.map(deviceUpdateBindingModel,
                DeviceUpdateServiceModel.class);
        serviceModel.setId(id);

        deviceService.updateOffer(serviceModel);

        return "redirect:/devices/" + id + "/device-page";
    }
    @GetMapping("/devices/add-device")
    public String getAddDevicePage(Model model) {

        if (!model.containsAttribute("deviceAddBindingModel")) {
            model.addAttribute("deviceAddBindingModel", new DeviceAddBindingModel());
        }
        return "add-device";
    }
    @PostMapping("/devices/add-device")
    public String addDevice(@Valid DeviceAddBindingModel deviceAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, @CurrentSecurityContext(expression = "authentication?.name") String name) {
        if(bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("deviceAddBindingModel",deviceAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.deviceAddBindingModel",bindingResult);
            return "redirect:/devices/add-device";
        }
        DeviceAddServiceModel deviceAddServiceModel = deviceService.addDevice(deviceAddBindingModel,name);
        return "redirect:/devices/" +deviceAddServiceModel.getId()+"/device-page";
    }
}
