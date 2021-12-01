package com.example.norcomapllication.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order  extends BaseEntity{
    public User user;
    public String phoneNumber;
    public Device device;
    public MobilePlan mobilePlan;

    @ManyToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }
    @Column(name = "phone_number",unique = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Order setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;

    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Device getDevice() {
        return device;
    }

    public Order setDevice(Device device) {
        this.device = device;
        return this;

    }
    @ManyToOne(fetch = FetchType.EAGER)
    public MobilePlan getMobilePlan() {
        return mobilePlan;
    }

    public Order setMobilePlan(MobilePlan mobilePlan) {
        this.mobilePlan = mobilePlan;
        return this;

    }

}
