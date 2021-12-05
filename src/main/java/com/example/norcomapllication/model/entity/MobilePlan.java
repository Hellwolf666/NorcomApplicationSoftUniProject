package com.example.norcomapllication.model.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "mobile_plans")
public class MobilePlan extends BaseEntity {
    private String name;
    private String mobilePlanType;
    private String internet;
    private String minutes;
    private String sms;
    private String internetEU;
    private String minutesEU;
    private String smsEU;
    private BigDecimal price;
    private Set<Order> orders;
    private User user;
    private String services;
    private Integer servicesCount;
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
    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public MobilePlan setPrice(BigDecimal price) {
        this.price = price;
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
    @ManyToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public MobilePlan setUser(User user) {
        this.user = user;
        return this;
    }
    @Column(name = "services")
    public String getServices() {
        return services;
    }

    public MobilePlan setServices(String services) {
        this.services = services; return this;
    }
    @Column(name = "service_count")
    public Integer getServicesCount() {
        return servicesCount;
    }

    public MobilePlan setServicesCount(Integer servicesCount) {
        this.servicesCount = servicesCount; return this;
    }

}
