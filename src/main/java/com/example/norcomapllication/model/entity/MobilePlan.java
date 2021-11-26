package com.example.norcomapllication.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "mobile_plans")
public class MobilePlan extends BaseEntity {
    private String name;
    public MobilePlanType mobilePlanType;
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

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public MobilePlanType getMobilePlanType() {
        return mobilePlanType;
    }

    public void setMobilePlanType(MobilePlanType mobilePlanType) {
        this.mobilePlanType = mobilePlanType;
    }
    @Column(nullable = false)
    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }
    @Column(nullable = false)
    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }
    @Column(nullable = false)
    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
    @Column(nullable = false)
    public String getInternetEU() {
        return internetEU;
    }

    public void setInternetEU(String internetEU) {
        this.internetEU = internetEU;
    }
    @Column(nullable = false)
    public String getMinutesEU() {
        return minutesEU;
    }

    public void setMinutesEU(String minutesEU) {
        this.minutesEU = minutesEU;
    }
    @Column(nullable = false)
    public String getSmsEU() {
        return smsEU;
    }

    public void setSmsEU(String smsEU) {
        this.smsEU = smsEU;
    }
    @ManyToMany(mappedBy = "mobilePlans")
    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
    @OneToMany(mappedBy = "mobilePlan")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
