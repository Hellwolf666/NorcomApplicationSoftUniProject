package com.example.norcomapllication.model.view;

import com.example.norcomapllication.model.entity.Role;

import java.util.Set;

public class AdminUsersViewModel {
    private Long id;
    private String username;
    private String fullName;
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public AdminUsersViewModel setId(Long id) {
        this.id = id; return this;
    }

    public String getUsername() {
        return username;
    }

    public AdminUsersViewModel setUsername(String username) {
        this.username = username;return this;
    }

    public String getFullName() {
        return fullName;
    }

    public AdminUsersViewModel setFullName(String fullName) {
        this.fullName = fullName;return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public AdminUsersViewModel setRoles(Set<Role> roles) {
        this.roles = roles;return this;
    }
}
