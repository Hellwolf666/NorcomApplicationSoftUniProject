package com.example.norcomapllication.model.entity;

import com.example.norcomapllication.model.entity.enums.MobilePlanType;
import com.example.norcomapllication.model.entity.enums.OperationSystemType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "devices")
public class Device extends BaseEntity{
    public String firstImageUrl;
    public String secondImageUrl;
    public String thirdImageUrl;
    public String fourthImageUrl;
    public String fifthImageUrl;
    public String name;
    public Double price;
    public MobilePlanType type;
    public OperationSystemType operationSystem;
    public String processor;
    public String chipset;
    public String sizeDevice;
    public Long weightDevice;
    public Long typeOfScreen;
    public Integer sizeOfScreen;
    public String resolutionOfScreen;
    public String cardMemory;
    public String deviceStorage;
    public String deviceRamStorage;
    public String backCamera;
    public String frontCamera;
    public String speaker;
    public String headphoneSlot;
    public String batteryCapacity;
    public User user;
    public Set<Order> orders;

    public Device() {
    }
    @Column(columnDefinition = "LONGTEXT")
    public String getFirstImageUrl() {
        return firstImageUrl;
    }

    public Device setFirstImageUrl(String firstImageUrl) {
        this.firstImageUrl = firstImageUrl;
        return this;
    }
    @Column(columnDefinition = "LONGTEXT")
    public String getSecondImageUrl() {
        return secondImageUrl;
    }

    public Device setSecondImageUrl(String secondImageUrl) {
        this.secondImageUrl = secondImageUrl;
        return this;
    }
    @Column(columnDefinition = "LONGTEXT")
    public String getThirdImageUrl() {
        return thirdImageUrl;
    }

    public Device setThirdImageUrl(String thirdImageUrl) {
        this.thirdImageUrl = thirdImageUrl;
        return this;
    }
    @Column(columnDefinition = "LONGTEXT")
    public String getFourthImageUrl() {
        return fourthImageUrl;
    }

    public Device setFourthImageUrl(String fourthImageUrl) {
        this.fourthImageUrl = fourthImageUrl;
        return this;
    }
    @Column(columnDefinition = "LONGTEXT")
    public String getFifthImageUrl() {
        return fifthImageUrl;
    }

    public Device setFifthImageUrl(String fifthImageUrl) {
        this.fifthImageUrl = fifthImageUrl;
        return this;
    }
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Device setName(String name) {
        this.name = name;
        return this;
    }
    @Column(nullable = false)
    public Double getPrice() {
        return price;
    }

    public Device setPrice(Double price) {
        this.price = price;
        return this;
    }
    @Enumerated(EnumType.STRING)
    public MobilePlanType getType() {
        return type;
    }

    public Device setType(MobilePlanType type) {
        this.type = type;
        return this;
    }
    @Enumerated(EnumType.STRING)
    public OperationSystemType getOperationSystem() {
        return operationSystem;
    }

    public Device setOperationSystem(OperationSystemType operationSystem) {
        this.operationSystem = operationSystem;
        return this;
    }
    @Column(nullable = false)
    public String getProcessor() {
        return processor;
    }

    public Device setProcessor(String processor) {
        this.processor = processor;
        return this;
    }
    @Column(nullable = false)
    public String getChipset() {
        return chipset;
    }

    public Device setChipset(String chipset) {
        this.chipset = chipset;
        return this;
    }
    @Column(name = "device_size",nullable = false)
    public String getSizeDevice() {
        return sizeDevice;
    }

    public Device setSizeDevice(String sizeDevice) {
        this.sizeDevice = sizeDevice;
        return this;
    }
    @Column(name="device_weight",nullable = false)
    public Long getWeightDevice() {
        return weightDevice;
    }

    public Device setWeightDevice(Long weightDevice) {
        this.weightDevice = weightDevice;
        return this;
    }
    @Column(name = "type_of_screen",nullable = false)
    public Long getTypeOfScreen() {
        return typeOfScreen;
    }

    public Device setTypeOfScreen(Long typeOfScreen) {
        this.typeOfScreen = typeOfScreen;
        return this;
    }
    @Column(name = "size_of_screen",nullable = false)
    public Integer getSizeOfScreen() {
        return sizeOfScreen;
    }

    public Device setSizeOfScreen(Integer sizeOfScreen) {
        this.sizeOfScreen = sizeOfScreen;
        return this;
    }
    @Column(name = "resolution_of_screen",nullable = false)
    public String getResolutionOfScreen() {
        return resolutionOfScreen;
    }

    public Device setResolutionOfScreen(String resolutionOfScreen) {
        this.resolutionOfScreen = resolutionOfScreen;
        return this;
    }
    @Column(name = "card_memory",nullable = false)
    public String getCardMemory() {
        return cardMemory;
    }

    public Device setCardMemory(String cardMemory) {
        this.cardMemory = cardMemory;
        return this;
    }
    @Column(name = "device_storage",nullable = false)
    public String getDeviceStorage() {
        return deviceStorage;
    }

    public Device setDeviceStorage(String deviceStorage) {
        this.deviceStorage = deviceStorage;
        return this;
    }
    @Column(name = "device_ram",nullable = false)
    public String getDeviceRamStorage() {
        return deviceRamStorage;
    }

    public Device setDeviceRamStorage(String deviceRamStorage) {
        this.deviceRamStorage = deviceRamStorage;
        return this;
    }
    @Column(name = "back_camera",nullable = false)
    public String getBackCamera() {
        return backCamera;
    }

    public Device setBackCamera(String backCamera) {
        this.backCamera = backCamera;
        return this;
    }
    @Column(name = "front_camera",nullable = false)
    public String getFrontCamera() {
        return frontCamera;
    }

    public Device setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
        return this;
    }
    @Column(name = "speaker",nullable = false)
    public String getSpeaker() {
        return speaker;
    }

    public Device setSpeaker(String speaker) {
        this.speaker = speaker;
        return this;
    }
    @Column(name = "headphone_slot",nullable = false)
    public String getHeadphoneSlot() {
        return headphoneSlot;
    }

    public Device setHeadphoneSlot(String headphoneSlot) {
        this.headphoneSlot = headphoneSlot;
        return this;
    }
    @Column(name = "battery_capacity",nullable = false)
    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public Device setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        return this;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public Device setUser(User user) {
        this.user = user;
        return this;
    }
    @OneToMany(mappedBy = "device")
    public Set<Order> getOrders() {
        return orders;
    }

    public Device setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }
}
