package com.example.norcomapllication.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ProfilleUpdateBindingModel {
    private Long id;
    private String address;
    private String email;

    public Long getId() {
        return id;
    }

    public ProfilleUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }
    @NotNull
    public String getAddress() {
        return address;
    }

    public ProfilleUpdateBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }
    @Email
    @NotNull
    public String getEmail() {
        return email;
    }

    public ProfilleUpdateBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
