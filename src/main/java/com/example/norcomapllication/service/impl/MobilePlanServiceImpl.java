package com.example.norcomapllication.service.impl;

import com.example.norcomapllication.model.binding.MobilePlanAddBindingModel;
import com.example.norcomapllication.model.entity.MobilePlan;
import com.example.norcomapllication.model.entity.Role;
import com.example.norcomapllication.model.entity.User;
import com.example.norcomapllication.model.entity.enums.RoleEnumClass;
import com.example.norcomapllication.model.service.MobilePlanAddServiceModel;
import com.example.norcomapllication.model.service.MobilePlanServiceUpdate;
import com.example.norcomapllication.model.view.MobilePlanDetailsView;
import com.example.norcomapllication.repository.MobilePlanRepository;
import com.example.norcomapllication.repository.UserRepository;
import com.example.norcomapllication.service.MobilePlanService;
import com.example.norcomapllication.service.errorPackage.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MobilePlanServiceImpl implements MobilePlanService {
    private final MobilePlanRepository mobilePlanRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public MobilePlanServiceImpl(MobilePlanRepository mobilePlanRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.mobilePlanRepository = mobilePlanRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MobilePlanDetailsView> getAll4GTypePlans(String fourG) {
        return this.mobilePlanRepository.getAllByMobilePlanType(fourG).stream().map(this::map).collect(Collectors.toList());
    }

    private MobilePlanDetailsView map(MobilePlan mobilePlan) {
        return this.modelMapper.map(mobilePlan, MobilePlanDetailsView.class);
    }

    @Override
    public List<MobilePlanDetailsView> getAll5GTypePlans(String fiveG) {
        return this.mobilePlanRepository.getAllByMobilePlanType(fiveG).stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public void deleteMobilePlan(Long id) {
        this.mobilePlanRepository.deleteById(id);
    }

    @Override
    public MobilePlanAddServiceModel addMobilePlan(MobilePlanAddBindingModel mobilePlanAddBindingModel, String userIdentifier) {
        User user = this.userRepository.findByUsername(userIdentifier).orElseThrow();
        MobilePlanAddServiceModel mobilePlanAddServiceModel = modelMapper.map(mobilePlanAddBindingModel, MobilePlanAddServiceModel.class);
        MobilePlan newMobilePlan = modelMapper.map(mobilePlanAddServiceModel, MobilePlan.class);
        newMobilePlan.setUser(user);
        MobilePlan addPlan = mobilePlanRepository.save(newMobilePlan);
        return modelMapper.map(addPlan, MobilePlanAddServiceModel.class);
    }

    @Override
    public MobilePlanDetailsView findById(Long id, String user) {
        return this.mobilePlanRepository.findById(id).map(mobilePlan -> mapMobilePlanDetailsView(user, mobilePlan)).get();
    }

    @Override
    public void updateMobilePlan(MobilePlanServiceUpdate mobilePlanServiceUpdate) {
        MobilePlan mobilePlan = mobilePlanRepository.findById(mobilePlanServiceUpdate.getId()).orElseThrow(() -> new ObjectNotFoundException("Mobile plan with id" + mobilePlanServiceUpdate.getId() + "not found!"));
        mobilePlan.setPrice(mobilePlanServiceUpdate.getPrice())
                .setName(mobilePlanServiceUpdate.getName())
                .setInternet(mobilePlanServiceUpdate.getInternet())
                .setInternetEU(mobilePlanServiceUpdate.getInternetEU())
                .setMinutes(mobilePlanServiceUpdate.getMinutes())
                .setMinutesEU(mobilePlanServiceUpdate.getMinutesEU())
                .setSms(mobilePlanServiceUpdate.getSms())
                .setSmsEU(mobilePlanServiceUpdate.getSmsEU())
                .setServices(mobilePlanServiceUpdate.getServices())
                .setServicesCount(mobilePlanServiceUpdate.getServicesCount());

        mobilePlanRepository.save(mobilePlan);
    }

    private MobilePlanDetailsView mapMobilePlanDetailsView(String user, MobilePlan mobilePlan) {
        MobilePlanDetailsView mobilePlanDetailsView = this.modelMapper.map(mobilePlan, MobilePlanDetailsView.class);
        mobilePlanDetailsView.setCanDelete(isOwner(user, mobilePlan.getId()));
        return mobilePlanDetailsView;
    }

    public boolean isOwner(String user, Long id) {
        Optional<MobilePlan> mobile = mobilePlanRepository.findById(id);
        Optional<User> byUsername = userRepository.findByUsername(user);
        if (mobile.isEmpty() || byUsername.isEmpty()) {
            return false;
        } else {
            MobilePlan mobilePlan = mobile.get();
            return isAdmin(byUsername.get()) || mobilePlan.getUser().getUsername().equals(user);
        }
    }

    @Override
    public Collection<MobilePlan> getAllPlans() {
        return mobilePlanRepository.getAllBy();
    }

    private boolean isAdmin(User user) {
        return user.getRoles().stream().map(Role::getRole).anyMatch(roleEnumClass -> roleEnumClass == RoleEnumClass.ADMIN);
    }
}
