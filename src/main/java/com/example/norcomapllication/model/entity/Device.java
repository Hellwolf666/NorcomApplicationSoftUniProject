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

    public void setFirstImageUrl(String firstImageUrl) {
        this.firstImageUrl = firstImageUrl;
    }
    @Column(columnDefinition = "LONGTEXT")
    public String getSecondImageUrl() {
        return secondImageUrl;
    }

    public void setSecondImageUrl(String secondImageUrl) {
        this.secondImageUrl = secondImageUrl;
    }
    @Column(columnDefinition = "LONGTEXT")
    public String getThirdImageUrl() {
        return thirdImageUrl;
    }

    public void setThirdImageUrl(String thirdImageUrl) {
        this.thirdImageUrl = thirdImageUrl;
    }
    @Column(columnDefinition = "LONGTEXT")
    public String getFourthImageUrl() {
        return fourthImageUrl;
    }

    public void setFourthImageUrl(String fourthImageUrl) {
        this.fourthImageUrl = fourthImageUrl;
    }
    @Column(columnDefinition = "LONGTEXT")
    public String getFifthImageUrl() {
        return fifthImageUrl;
    }

    public void setFifthImageUrl(String fifthImageUrl) {
        this.fifthImageUrl = fifthImageUrl;
    }
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    @Enumerated(EnumType.STRING)
    public MobilePlanType getType() {
        return type;
    }

    public void setType(MobilePlanType type) {
        this.type = type;
    }
    @Enumerated(EnumType.STRING)
    public OperationSystemType getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(OperationSystemType operationSystem) {
        this.operationSystem = operationSystem;
    }
    @Column(nullable = false)
    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }
    @Column(nullable = false)
    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }
    @Column(name = "device_size",nullable = false)
    public String getSizeDevice() {
        return sizeDevice;
    }

    public void setSizeDevice(String sizeDevice) {
        this.sizeDevice = sizeDevice;
    }
    @Column(name="device_weight",nullable = false)
    public Long getWeightDevice() {
        return weightDevice;
    }

    public void setWeightDevice(Long weightDevice) {
        this.weightDevice = weightDevice;
    }
    @Column(name = "type_of_screen",nullable = false)
    public Long getTypeOfScreen() {
        return typeOfScreen;
    }

    public void setTypeOfScreen(Long typeOfScreen) {
        this.typeOfScreen = typeOfScreen;
    }
    @Column(name = "size_of_screen",nullable = false)
    public Integer getSizeOfScreen() {
        return sizeOfScreen;
    }

    public void setSizeOfScreen(Integer sizeOfScreen) {
        this.sizeOfScreen = sizeOfScreen;
    }
    @Column(name = "resolution_of_screen",nullable = false)
    public String getResolutionOfScreen() {
        return resolutionOfScreen;
    }

    public void setResolutionOfScreen(String resolutionOfScreen) {
        this.resolutionOfScreen = resolutionOfScreen;
    }
    @Column(name = "card_memory",nullable = false)
    public String getCardMemory() {
        return cardMemory;
    }

    public void setCardMemory(String cardMemory) {
        this.cardMemory = cardMemory;
    }
    @Column(name = "device_storage",nullable = false)
    public String getDeviceStorage() {
        return deviceStorage;
    }

    public void setDeviceStorage(String deviceStorage) {
        this.deviceStorage = deviceStorage;
    }
    @Column(name = "device_ram",nullable = false)
    public String getDeviceRamStorage() {
        return deviceRamStorage;
    }

    public void setDeviceRamStorage(String deviceRamStorage) {
        this.deviceRamStorage = deviceRamStorage;
    }
    @Column(name = "back_camera",nullable = false)
    public String getBackCamera() {
        return backCamera;
    }

    public void setBackCamera(String backCamera) {
        this.backCamera = backCamera;
    }
    @Column(name = "front_camera",nullable = false)
    public String getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
    }
    @Column(name = "speaker",nullable = false)
    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
    @Column(name = "headphone_slot",nullable = false)
    public String getHeadphoneSlot() {
        return headphoneSlot;
    }

    public void setHeadphoneSlot(String headphoneSlot) {
        this.headphoneSlot = headphoneSlot;
    }
    @Column(name = "battery_capacity",nullable = false)
    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @OneToMany(mappedBy = "device")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
