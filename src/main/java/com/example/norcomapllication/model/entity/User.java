package com.example.norcomapllication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User  extends BaseEntity{
    public String fullName;
    public String address;
    public String email;
    public String gender;
    public String username;
    public String password;
    public Set<Order> orders;
    private Set<Role> roles = new HashSet<>();
    private Set<Device> devices;

    public User() {
    }
    @Column(unique = true,nullable = false)
    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Column(nullable = false)

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;

    }
    @Column(unique = true,nullable = false)
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;

    }
    @Column(nullable = false)
    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;

    }

    @Column(unique = true,nullable = false)
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;

    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;

    }

    @OneToMany(mappedBy = "user")
    public Set<Order> getOrder() {
        return orders;
    }

    public User setOrder(Set<Order> orders) {
        this.orders = orders;
        return this;

    }
    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Role> getRoles() {
        return roles;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;

    }
    @OneToMany
    public Set<Device> getDevices() {
        return devices;
    }

    public User setDevices(Set<Device> devices) {
        this.devices = devices;
        return this;

    }
}
