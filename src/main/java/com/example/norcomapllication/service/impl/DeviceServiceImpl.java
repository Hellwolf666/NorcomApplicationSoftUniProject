package com.example.norcomapllication.service.impl;

import com.example.norcomapllication.model.binding.DeviceAddBindingModel;
import com.example.norcomapllication.model.entity.Device;
import com.example.norcomapllication.model.entity.Role;
import com.example.norcomapllication.model.entity.User;
import com.example.norcomapllication.model.entity.enums.OperationSystemType;
import com.example.norcomapllication.model.entity.enums.RoleEnumClass;
import com.example.norcomapllication.model.service.DeviceAddServiceModel;
import com.example.norcomapllication.model.service.DeviceUpdateServiceModel;
import com.example.norcomapllication.model.view.DeviceDetailsView;
import com.example.norcomapllication.model.view.DeviceSummaryView;
import com.example.norcomapllication.repository.DeviceRepository;
import com.example.norcomapllication.repository.UserRepository;
import com.example.norcomapllication.service.DeviceService;
import com.example.norcomapllication.service.errorPackage.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public DeviceServiceImpl(DeviceRepository deviceRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DeviceSummaryView> getAllDevices() {
        return deviceRepository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public DeviceDetailsView findById(Long id, String name) {
        return deviceRepository.findById(id).map(device -> mapDeviceDetailsView(name,device)).get();
    }

    private DeviceDetailsView mapDeviceDetailsView(String name, Device device) {
        DeviceDetailsView deviceDetailsView = this.modelMapper.map(device,DeviceDetailsView.class);
        deviceDetailsView.setCanDelete(isOwner(name,device.getId()));
        return deviceDetailsView;
    }

    public boolean isOwner(String name, Long id) {
        Optional<Device> deviceOptinal = deviceRepository.findById(id);
        Optional<User> userOptional = userRepository.findById(id);
        if(deviceOptinal.isEmpty() || userOptional.isEmpty()) {
            return false;
        } else {
            Device device = deviceOptinal.get();
            return isAdmin(userOptional.get()) || device.getUser().getUsername().equals(name);
        }
    }

    @Override
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public void updateOffer(DeviceUpdateServiceModel serviceModel) {
        Device device=deviceRepository.findById(serviceModel.getId()).orElseThrow(()->new ObjectNotFoundException("Device with this id: "+serviceModel.getId()+"not found!"));
        device.setFirstImageUrl(serviceModel.getFirstImageUrl())
                .setSecondImageUrl(serviceModel.getSecondImageUrl())
                .setThirdImageUrl(serviceModel.getThirdImageUrl())
                .setFourthImageUrl(serviceModel.getFourthImageUrl())
                .setFifthImageUrl(serviceModel.getFifthImageUrl())
                .setName(serviceModel.getName())
                .setPrice(serviceModel.getPrice())
                .setType(serviceModel.getType())
                .setOperationSystem(serviceModel.getOperationSystem())
                .setProcessor(serviceModel.getProcessor())
                .setChipset(serviceModel.getChipset())
                .setSizeDevice(serviceModel.getSizeDevice())
                .setWeightDevice(serviceModel.getWeightDevice())
                .setTypeOfScreen(serviceModel.getTypeOfScreen())
                .setSizeOfScreen(serviceModel.getSizeOfScreen())
                .setResolutionOfScreen(serviceModel.getResolutionOfScreen())
                .setCardMemory(serviceModel.getCardMemory())
                .setDeviceStorage(serviceModel.getDeviceStorage())
                .setDeviceRamStorage(serviceModel.getDeviceRamStorage())
                .setBackCamera(serviceModel.getBackCamera())
                .setFrontCamera(serviceModel.getFrontCamera())
                .setSpeaker(serviceModel.getSpeaker())
                .setHeadphoneSlot(serviceModel.getHeadphoneSlot())
                .setBatteryCapacity(serviceModel.getBatteryCapacity());
        deviceRepository.save(device);
    }

    @Override
    public DeviceAddServiceModel addDevice(DeviceAddBindingModel deviceAddBindingModel, String userIdentifier) {
        User user = userRepository.findByUsername(userIdentifier).orElseThrow();
        DeviceAddServiceModel deviceAddServiceModel = modelMapper.map(deviceAddBindingModel,DeviceAddServiceModel.class);
        Device newDevice = modelMapper.map(deviceAddServiceModel,Device.class);
        newDevice.setUser(user);
        Device savedOffer = deviceRepository.save(newDevice);
        return modelMapper.map(savedOffer,DeviceAddServiceModel.class);
    }



    private boolean isAdmin(User user) {
        return user.getRoles().stream().map(Role::getRole).allMatch(roleEnumClass -> roleEnumClass == RoleEnumClass.ADMIN);
    }

    private DeviceSummaryView map(Device device) {

        return this.modelMapper
                .map(device, DeviceSummaryView.class);
    }

    @Override
    public void initDevices() {
        if(deviceRepository.count() == 0) {
            Device samsung = new Device();
            samsung.setFirstImageUrl("https://s13emagst.akamaized.net/products/34408/34407359/images/res_2b0fa73eba769c1da5c288f35c92dc01.jpg")
                    .setSecondImageUrl("https://i.guim.co.uk/img/media/460229e455cd38808a11b1d0ebe866fcfd5f06ae/373_437_4638_2783/master/4638.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=a738f3c6316aaa69b8ffffbd56933c78")
                    .setThirdImageUrl("https://images.samsung.com/bg/smartphones/galaxy-s21-ultra-5g/images/galaxy-s21-ultra-5g-share-image.jpg")
                    .setFourthImageUrl("https://www.counterpointresearch.com/wp-content/uploads/2021/08/counterpoint-samsung-galaxy-s21-ultra-review.jpg")
                    .setFifthImageUrl("https://icdn.digitaltrends.com/image/digitaltrends/galaxy-s21-ultra-bottom-ports-500x500.jpg")
                    .setName("Samsung Galaxy S21 Ultra")
                    .setPrice(new BigDecimal("2530.00"))
                    .setType("5G")
                    .setOperationSystem(OperationSystemType.Android)
                    .setProcessor("Octa-core (1x2.84 GHz Kryo 680 & 3x2.42 GHz Kryo 680 & 4x1.80 GHz Kryo 680) ")
                    .setChipset("Snapdragon 888 5G (5 nm)")
                    .setSizeDevice("165.1 x 75.6 x 8.9 mm")
                    .setWeightDevice(227L)
                    .setTypeOfScreen("Dynamic AMOLED 2X, 120Hz, HDR10+, 1500 nits (peak)")
                    .setSizeOfScreen(new BigDecimal("6.8"))
                    .setResolutionOfScreen("1440 x 3200")
                    .setCardMemory("няма")
                    .setDeviceStorage("256 GB")
                    .setDeviceRamStorage("12 GB")
                    .setBackCamera("108 MP, f/1.8, 24mm (wide), 1/1.33\", 0.8µm, PDAF, Laser AF, OIS\n" +
                            "10 MP, f/4.9, 240mm (periscope telephoto), 1/3.24\", 1.22µm, dual pixel PDAF, OIS, 10x optical zoom\n" +
                            "10 MP, f/2.4, 72mm (telephoto), 1/3.24\", 1.22µm, dual pixel PDAF, OIS, 3x optical zoom\n" +
                            "12 MP, f/2.2, 13mm (ultrawide), 1/2.55\", 1.4µm, dual pixel PDAF, Super Steady video\n")
                    .setFrontCamera("40 MP, f/2.2, 26mm (wide), 1/2.8\", 0.7µm, PDAF")
                    .setSpeaker("Има")
                    .setHeadphoneSlot("няма")
                    .setBatteryCapacity("5000 mAh");
            deviceRepository.save(samsung);
        }
    }
}
