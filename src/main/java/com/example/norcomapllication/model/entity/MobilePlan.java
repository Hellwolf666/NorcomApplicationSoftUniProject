package com.example.norcomapllication.model.entity;

import com.example.norcomapllication.model.entity.enums.MobilePlanType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mobile_plans")
public class MobilePlan extends BaseEntity {
    private String name;
    public String mobilePlanType;
    public String internet;
    public String minutes;
    public String sms;
    public String internetEU;
    public String minutesEU;
    public String smsEU;
    public Set<Service> services;
    public Set<Order> orders;
    public MobilePlan() {
    }
    @Column(nullable = false,unique = true)
    public String getName() {
        return name;
    }

    public MobilePlan setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    public String getMobilePlanType() {
        return mobilePlanType;
    }

    public MobilePlan setMobilePlanType(String mobilePlanType) {
        this.mobilePlanType = mobilePlanType;
        return this;
    }
    @Column(nullable = false)
    public String getInternet() {
        return internet;
    }

    public MobilePlan setInternet(String internet) {
        this.internet = internet;
        return this;
    }
    @Column(nullable = false)
    public String getMinutes() {
        return minutes;
    }

    public MobilePlan setMinutes(String minutes) {
        this.minutes = minutes;
        return this;
    }
    @Column(nullable = false)
    public String getSms() {
        return sms;
    }

    public MobilePlan setSms(String sms) {
        this.sms = sms;
        return this;
    }
    @Column(nullable = false)
    public String getInternetEU() {
        return internetEU;
    }

    public MobilePlan setInternetEU(String internetEU) {
        this.internetEU = internetEU;
        return this;
    }
    @Column(nullable = false)
    public String getMinutesEU() {
        return minutesEU;
    }

    public MobilePlan setMinutesEU(String minutesEU) {
        this.minutesEU = minutesEU;
        return this;
    }
    @Column(nullable = false)
    public String getSmsEU() {
        return smsEU;
    }

    public MobilePlan setSmsEU(String smsEU) {
        this.smsEU = smsEU;
        return this;
    }
    @ManyToMany(mappedBy = "mobilePlans")
    public Set<Service> getServices() {
        return services;
    }

    public MobilePlan setServices(Set<Service> services) {
        this.services = services;
        return this;
    }
    @OneToMany(mappedBy = "mobilePlan")
    public Set<Order> getOrders() {
        return orders;
    }

    public MobilePlan setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }
}
