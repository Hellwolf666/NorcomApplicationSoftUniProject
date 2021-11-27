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

    public void setUser(User user) {
        this.user = user;
    }
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    public MobilePlan getMobilePlan() {
        return mobilePlan;
    }

    public void setMobilePlan(MobilePlan mobilePlan) {
        this.mobilePlan = mobilePlan;
    }

}
