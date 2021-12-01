package com.example.norcomapllication.model.service;

public class ProfileUpdateServiceModel {
    private Long id;
    private String email;
    private String address;

    public Long getId() {
        return id;
    }

    public ProfileUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileUpdateServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ProfileUpdateServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }
}
