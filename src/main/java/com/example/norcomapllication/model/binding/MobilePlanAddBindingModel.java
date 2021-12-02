package com.example.norcomapllication.model.binding;

import com.example.norcomapllication.model.entity.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Set;

public class MobilePlanAddBindingModel {
    private Long id;
    private String name;
    private String mobilePlanType;
    private String internet;
    private String minutes;
    private String sms;
    private String internetEU;
    private String minutesEU;
    private String smsEU;
    private BigDecimal price;
    private Set<Service> services;

    public Long getId() {
        return id;
    }

    public MobilePlanAddBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public MobilePlanAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    public String getMobilePlanType() {
        return mobilePlanType;
    }

    public MobilePlanAddBindingModel setMobilePlanType(String mobilePlanType) {
        this.mobilePlanType = mobilePlanType;
        return this;
    }

    @NotNull
    public String getInternet() {
        return internet;
    }

    public MobilePlanAddBindingModel setInternet(String internet) {
        this.internet = internet;
        return this;
    }

    @NotNull
    public String getMinutes() {
        return minutes;
    }

    public MobilePlanAddBindingModel setMinutes(String minutes) {
        this.minutes = minutes;
        return this;
    }

    @NotNull
    public String getSms() {
        return sms;
    }

    public MobilePlanAddBindingModel setSms(String sms) {
        this.sms = sms;
        return this;
    }

    @NotNull
    public String getInternetEU() {
        return internetEU;
    }

    public MobilePlanAddBindingModel setInternetEU(String internetEU) {
        this.internetEU = internetEU;
        return this;
    }

    @NotNull
    public String getMinutesEU() {
        return minutesEU;
    }

    public MobilePlanAddBindingModel setMinutesEU(String minutesEU) {
        this.minutesEU = minutesEU;
        return this;
    }

    @NotNull
    public String getSmsEU() {
        return smsEU;
    }

    public MobilePlanAddBindingModel setSmsEU(String smsEU) {
        this.smsEU = smsEU;
        return this;
    }

    @NotNull
    @PositiveOrZero
    public BigDecimal getPrice() {
        return price;
    }

    public MobilePlanAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Set<Service> getServices() {
        return services;
    }

    public MobilePlanAddBindingModel setServices(Set<Service> services) {
        this.services = services; return this;
    }

}
